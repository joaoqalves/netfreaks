package net.joaoqalves;

import net.joaoqalves.domain.customer.Customer;
import net.joaoqalves.domain.film.Film;
import net.joaoqalves.domain.film.FilmType;
import net.joaoqalves.domain.rental.Rental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Date;

public class Seed {

    public static void createData(final SessionFactory sessionFactory) {

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        try {
            Customer customer1 = new Customer();
            customer1.setName("John Snow");
            customer1.setPoints(3L); // 2 because of Matrix and 1 because of Spiderman

            session.persist(customer1);

            Film matrix11 = new Film();
            matrix11.setName("Matrix 11");
            matrix11.setFilmType(FilmType.NEW);
            matrix11.setTotal(2);
            matrix11.setNumAvailable(1); // One is rented by default to John Snow

            Film spiderman = new Film();
            spiderman.setName("Spiderman");
            spiderman.setFilmType(FilmType.REGULAR);
            spiderman.setTotal(2);
            spiderman.setNumAvailable(1); // One is rented by default to John Snow

            Film spiderman2 = new Film();
            spiderman2.setName("Spiderman 2");
            spiderman2.setFilmType(FilmType.REGULAR);
            spiderman2.setTotal(1);
            spiderman2.setNumAvailable(1);

            Film outOfAfrica = new Film();
            outOfAfrica.setName("Out of Africa");
            outOfAfrica.setFilmType(FilmType.OLD);
            outOfAfrica.setTotal(1);
            outOfAfrica.setNumAvailable(1);

            Rental rental1 = new Rental();
            rental1.setCustomer(customer1);
            rental1.setFilm(spiderman);
            rental1.setRentedAt(new Date(new Date().getTime() - 30*60*60*1000));
            Long days1 = 1L;
            rental1.setDays(days1);

            session.persist(matrix11);
            session.persist(spiderman);
            session.persist(spiderman2);
            session.persist(outOfAfrica);

            Rental rental2 = new Rental();
            rental2.setCustomer(customer1);
            rental2.setFilm(matrix11);
            rental2.setRentedAt(new Date(new Date().getTime() - 60*60*60*1000));
            Long days2 = 1L;
            rental2.setDays(days2);

            session.persist(rental1);
            session.persist(rental2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            session.close();
        }
    }

}
