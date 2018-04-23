package com.springboot.bak.parking.model;

public class CurrenciesAllowed {
    private String currencyName;
    private String isoCode;
    // for PLN rate = 1
    private double rate;

    public CurrenciesAllowed(String currencyName, String isoCode, double rate) {
        this.currencyName = currencyName;
        this.isoCode = isoCode;
        this.rate = rate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
