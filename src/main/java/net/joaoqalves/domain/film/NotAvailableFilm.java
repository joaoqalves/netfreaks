package net.joaoqalves.domain.film;

import net.joaoqalves.domain.film.Film;

public class NotAvailableFilm {

    private Film film;

    public NotAvailableFilm() {
    }

    public NotAvailableFilm(Film film) {
        this.film = film;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
