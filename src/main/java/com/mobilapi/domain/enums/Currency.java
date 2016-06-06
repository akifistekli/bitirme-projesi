package com.mobilapi.domain.enums;


public enum Currency {

    Turkhish_lira("TL"),
    Usd_Dolar("$");

    private String currency;

    private Currency(String currency) {
        this.currency = currency;
    }

    public String getCurrency() {
        return currency;
    }
}
