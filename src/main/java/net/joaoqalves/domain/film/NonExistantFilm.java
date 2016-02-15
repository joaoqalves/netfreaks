package net.joaoqalves.domain.film;

public class NonExistantFilm {

    private Integer filmId;

    public NonExistantFilm() {
    }

    public NonExistantFilm(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
}
