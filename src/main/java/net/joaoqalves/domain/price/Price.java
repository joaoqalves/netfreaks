package net.joaoqalves.domain.price;

import net.joaoqalves.domain.currency.Currency;

public class Price {

    private Float value;
    private Currency currency;


    public Price() {
    }

    public Price(Float value, Currency currency) {
        this.value = value;
        this.currency = currency;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Price multiply(final Number number) {
        return new Price(value * number.floatValue(), currency);
    }

    public Price plus(final Price price) {
        assert(currency.equals(price.getCurrency()));
        return new Price(value + price.getValue(), currency);
    }
}
