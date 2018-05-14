package com.springboot.bak.parking.model;

public class Currency {

    public static final Currency PLN = new Currency("PLN", "PLN", 1);

    private final String currencyName;
    private final String isoCode;
    private final double rate;

    private Currency(String currencyName, String isoCode, double rate) {
        this.currencyName = currencyName;
        this.isoCode = isoCode;
        this.rate = rate;
    }

    public double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return isoCode;
    }
}
