package net.joaoqalves.domain.returnfilm;

import net.joaoqalves.domain.price.PriceResponse;
import net.joaoqalves.domain.rental.SurchargedRental;

import java.util.ArrayList;
import java.util.List;

public class ReturnResponse extends PriceResponse {

    private List<SurchargedRental> surchargedRentals = new ArrayList<>();

    public List<SurchargedRental> getSurchargedRentals() {
        return surchargedRentals;
    }

    public void setSurchargedRentals(List<SurchargedRental> surchargedRentals) {
        this.surchargedRentals = surchargedRentals;
    }
}
