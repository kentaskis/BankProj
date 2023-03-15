package com.project.bankproj.entity.enums;

public enum CurrencyType {
    EUR(0),
    USD(1),
    UAH(2),
    RUB(3),
    BTC(4);

    private final int value;

    CurrencyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
