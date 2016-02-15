package net.joaoqalves.domain;


import net.joaoqalves.domain.film.FilmType;
import net.joaoqalves.domain.price.Price;

public class Utils {

    public static Price calculatePrice(final FilmType filmType, final Long days) {
        return filmType.getPrice().plus(filmType.getPrice().multiply(Math.max(0, days - filmType.getDayThreshold())));
    }

    public static Price calculateSurcharge(final FilmType filmType, final Long days) {
        return filmType.getPrice().multiply(days);
    }

}
