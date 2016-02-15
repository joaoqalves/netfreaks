package net.joaoqalves.services;

import javafx.util.Pair;
import net.joaoqalves.dao.RentalDAO;
import net.joaoqalves.domain.*;
import net.joaoqalves.domain.customer.Customer;
import net.joaoqalves.domain.customer.CustomerNotExists;
import net.joaoqalves.domain.film.Film;
import net.joaoqalves.domain.film.LockErrors;
import net.joaoqalves.domain.film.NonExistantFilm;
import net.joaoqalves.domain.film.NotAvailableFilm;
import net.joaoqalves.domain.price.Price;
import net.joaoqalves.domain.price.PriceResponse;
import net.joaoqalves.domain.rental.NewRental;
import net.joaoqalves.domain.rental.Rental;
import net.joaoqalves.domain.rental.RentalResponse;
import net.joaoqalves.domain.rental.SurchargedRental;
import net.joaoqalves.domain.returnfilm.NewReturn;
import net.joaoqalves.domain.returnfilm.ReturnResponse;
import org.hibernate.Session;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class RentalService {

    private CustomerService customerService;
    private FilmService filmService;
    private RentalDAO rentalDAO;

    public RentalService(final CustomerService customerService, final FilmService filmService,
                         final RentalDAO rentalDAO) {
        this.customerService = customerService;
        this.filmService = filmService;
        this.rentalDAO = rentalDAO;
    }

    public List<Rental> getAll(final Session session) {
        return rentalDAO.getAll(session);
    }

    public RentalResponse rentFilms(final Session session, final Integer customerId, final List<NewRental> rentals)
            throws CustomerNotExists {

        Customer customer = customerService.getOne(session, customerId)
                .orElseThrow(() -> new CustomerNotExists(Integer.toString(customerId)));
        RentalResponse rentalResponse = new RentalResponse();

        for(NewRental newRental: rentals) {
            Pair<Optional<Film>, LockErrors> filmStock = filmService.lockForRent(session, newRental.getFilmId());

            if(filmStock.getValue().equals(LockErrors.NO_STOCK_REMAINING)) {
                rentalResponse.getNotAvailableFilms().add(new NotAvailableFilm(filmStock.getKey().get()));
            } else if(filmStock.getValue().equals(LockErrors.FILM_NOT_EXISTS)) {
                rentalResponse.getNonExistantFilms().add(new NonExistantFilm(newRental.getFilmId()));
            } else {
                Film film = filmStock.getKey().get();
                Rental rental = new Rental();
                rental.setCustomer(customer);
                rental.setFilm(film);
                rental.setRentedAt(new Date());
                rental.setDays(newRental.getDays());
                rentalResponse.getRentals().add(rental);
                rentalResponse.addPrice(Utils.calculatePrice(film.getFilmType(), newRental.getDays()));
                rentalResponse.addBonusPoints(film.getFilmType().getBonusPoints());
                rentalDAO.save(session, rental);
            }
        }
        customer.setPoints(customer.getPoints() + rentalResponse.getBonusPoints());
        customerService.save(session, customer);

        return rentalResponse;
    }

    public PriceResponse returnFilms(Session session, Integer customerId, List<NewReturn> returns)
            throws CustomerNotExists {

        Customer customer = customerService.getOne(session, customerId)
                .orElseThrow(() -> new CustomerNotExists(Integer.toString(customerId)));
        ReturnResponse returnResponse = new ReturnResponse();

        List<Rental> rentals = rentalDAO.findToDeliverByCustomer(session, customer);
        Date now = new Date();

        for(NewReturn newReturn: returns) {
            Optional<Rental> optRental = rentals.stream().filter(r -> r.getId().equals(newReturn.getRentalId())).findFirst();

            if(optRental.isPresent()) {
                Rental rental = optRental.get();
                Long days = TimeUnit.DAYS.convert(now.getTime() - rental.getRentedAt().getTime(), TimeUnit.MILLISECONDS);
                System.out.println(days);
                rental.setDeliveredAt(now);
                Price price = Utils.calculateSurcharge(rental.getFilm().getFilmType(), days);
                returnResponse.addPrice(price);
                returnResponse.getSurchargedRentals().add(new SurchargedRental(rental, price));
                rentalDAO.save(session, rental);
                filmService.returnFilm(session, rental.getFilm().getId());
            } else {
                // IGNORE. Perhaps we should handle this
            }
        }

        return returnResponse;
    }
}
