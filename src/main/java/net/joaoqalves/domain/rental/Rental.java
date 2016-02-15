package net.joaoqalves.domain.rental;


import net.joaoqalves.domain.customer.Customer;
import net.joaoqalves.domain.film.Film;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rentals")
public class Rental {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(cascade=CascadeType.MERGE)
    private Customer customer;
    @ManyToOne(cascade=CascadeType.MERGE)
    private Film film;
    private Date rentedAt;
    private Long days;
    private Date deliveredAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Date getRentedAt() {
        return rentedAt;
    }

    public void setRentedAt(Date rentedAt) {
        this.rentedAt = rentedAt;
    }

    public Long getDays() {
        return days;
    }

    public void setDays(Long days) {
        this.days = days;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
    }
}
