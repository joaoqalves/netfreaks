package net.joaoqalves.domain.rental;

import net.joaoqalves.domain.price.Price;

public class SurchargedRental {

    private Rental rental;
    private Price surcharge;

    public SurchargedRental() {
    }

    public SurchargedRental(Rental rental, Price surcharge) {
        this.rental = rental;
        this.surcharge = surcharge;
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public Price getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(Price surcharge) {
        this.surcharge = surcharge;
    }
}
