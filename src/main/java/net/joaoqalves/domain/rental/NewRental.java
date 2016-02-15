package net.joaoqalves.domain.rental;

public class NewRental {

    private Integer filmId;
    private Long days;

    public NewRental() {
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }
}
