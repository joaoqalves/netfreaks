package net.joaoqalves.dao;

import net.joaoqalves.domain.film.Film;

public class FilmDAO extends AbstractDAO<Integer, Film> {

    public FilmDAO() {
        super(Film.class);
    }

    public FilmDAO(final Class<Film> clazz) {
        super(clazz);
    }
}
