package net.joaoqalves.domain.film;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "films")
public class Film {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private FilmType filmType;
    private Integer total;
    private Integer numAvailable;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FilmType getFilmType() {
        return filmType;
    }

    public void setFilmType(FilmType filmType) {
        this.filmType = filmType;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getNumAvailable() {
        return numAvailable;
    }

    public void setNumAvailable(Integer numAvailable) {
        this.numAvailable = numAvailable;
    }
}
