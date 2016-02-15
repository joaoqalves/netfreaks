package net.joaoqalves.domain.price;

import net.joaoqalves.domain.currency.Currencies;

public class PriceType {
    public static final Price BASIC = new Price(30f, Currencies.SEK);
    public static final Price PREMIUM = new Price(40f, Currencies.SEK);
}
