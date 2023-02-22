package com.project.bankproj.entity.enums;

public enum TransactionType {
    NEW(1),
    PENDING(4),
    APPROVED(8);
    private final int value;

    TransactionType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}