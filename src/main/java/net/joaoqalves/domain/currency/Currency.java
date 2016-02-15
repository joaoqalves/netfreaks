package net.joaoqalves.domain.currency;

public class Currency {

    private String name;
    private String isoCode;
    private String symbol;

    public Currency(String name, String isoCode, String symbol) {
        this.name = name;
        this.isoCode = isoCode;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
