package com.project.bankproj.entity.enums;

public enum Currencies {
    EUR(1),
    USD(2),
    UAH(3),
    RUB(4);

    private int value;

    private Currencies(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
