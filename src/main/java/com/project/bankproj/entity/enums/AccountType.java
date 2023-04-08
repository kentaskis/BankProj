package com.project.bankproj.entity.enums;

public enum AccountType {
    CREDIT(0),
    DEPOSIT(1),
    CURRENT(2);
    private final int value;

    AccountType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}