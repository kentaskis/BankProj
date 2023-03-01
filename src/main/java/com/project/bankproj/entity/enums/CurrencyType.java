package com.project.bankproj.entity.enums;

public enum CurrencyType {
    EUR(1),
    USD(2),
    UAH(3),
    RUB(4);

    private final int value;

    CurrencyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
