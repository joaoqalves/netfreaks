package net.joaoqalves.domain.price;

import net.joaoqalves.domain.currency.Currencies;

public class PriceResponse {

    private Price total = new Price(0f, Currencies.SEK);

    public Price getTotal() {
        return total;
    }

    public void setTotal(Price total) {
        this.total = total;
    }

    public Price addPrice(final Price price) {
        total = total.plus(price);
        return total;
    }

}
