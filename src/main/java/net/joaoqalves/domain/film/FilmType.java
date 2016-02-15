package net.joaoqalves.domain.film;

import net.joaoqalves.domain.price.Price;
import net.joaoqalves.domain.price.PriceType;

public enum FilmType {
    NEW(1L, PriceType.PREMIUM, 2L),
    REGULAR(3L, PriceType.BASIC, 1L),
    OLD(5L, PriceType.BASIC, 1L);

    private final Long dayThreshold;
    private final Price price;
    private final Long bonusPoints;

    FilmType(final Long dayThreshold, final Price price, final Long bonusPoints) {
        this.dayThreshold = dayThreshold;
        this.price = price;
        this.bonusPoints = bonusPoints;
    }

    public Long getDayThreshold() {
        return dayThreshold;
    }

    public Price getPrice() {
        return price;
    }

    public Long getBonusPoints() {
        return bonusPoints;
    }
}
