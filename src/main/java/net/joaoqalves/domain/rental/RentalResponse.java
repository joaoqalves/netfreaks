package net.joaoqalves.domain.rental;

import net.joaoqalves.domain.film.NonExistantFilm;
import net.joaoqalves.domain.film.NotAvailableFilm;
import net.joaoqalves.domain.price.PriceResponse;
import net.joaoqalves.domain.rental.Rental;

import java.util.ArrayList;
import java.util.List;

public class RentalResponse extends PriceResponse {

    private List<Rental> rentals = new ArrayList<>();
    private List<NotAvailableFilm> notAvailableFilms = new ArrayList<>();
    private List<NonExistantFilm> nonExistantFilms = new ArrayList<>();
    private Long bonusPoints = 0L;

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<NotAvailableFilm> getNotAvailableFilms() {
        return notAvailableFilms;
    }

    public void setNotAvailableFilms(List<NotAvailableFilm> notAvailableFilms) {
        this.notAvailableFilms = notAvailableFilms;
    }

    public List<NonExistantFilm> getNonExistantFilms() {
        return nonExistantFilms;
    }

    public void setNonExistantFilms(List<NonExistantFilm> nonExistantFilms) {
        this.nonExistantFilms = nonExistantFilms;
    }

    public Long getBonusPoints() {
        return bonusPoints;
    }

    public void setBonusPoints(Long bonusPoints) {
        this.bonusPoints = bonusPoints;
    }


    public Long addBonusPoints(final Long bonusPoints) {
        this.bonusPoints += bonusPoints;
        return this.bonusPoints;
    }
}
