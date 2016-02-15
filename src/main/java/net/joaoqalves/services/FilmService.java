package net.joaoqalves.services;

import javafx.util.Pair;
import net.joaoqalves.dao.FilmDAO;
import net.joaoqalves.domain.film.Film;
import net.joaoqalves.domain.film.LockErrors;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

public class FilmService {

    private FilmDAO filmDAO;

    public FilmService(final FilmDAO filmDAO) {
        this.filmDAO = filmDAO;
    }

    public List<Film> getAll(final Session session) {
        return filmDAO.getAll(session);
    }

    public Optional<Film> getOne(final Session session, final Integer id) {
        return filmDAO.getOne(session, id);
    }


    // Naive strategy. No concurrency handling / locking
    public Pair<Optional<Film>, LockErrors> lockForRent(final Session session, final Integer filmId) {
        return filmDAO.getOne(session, filmId).map(f -> {
            if(f.getNumAvailable() > 0) {
                f.setNumAvailable(f.getNumAvailable() - 1);
                filmDAO.save(session, f);
                return new Pair<>(Optional.of(f), LockErrors.NONE);
            } else {
                return new Pair<>(Optional.of(f), LockErrors.NO_STOCK_REMAINING);
            }
        }).orElse(new Pair<>(Optional.<Film>empty(), LockErrors.FILM_NOT_EXISTS));
    }

    // Again: naive strategy
    public void returnFilm(final Session session, Integer filmId) {
        filmDAO.getOne(session, filmId).ifPresent(f -> {
                f.setNumAvailable(f.getNumAvailable() + 1);
                filmDAO.save(session, f);
        });
    }
}
