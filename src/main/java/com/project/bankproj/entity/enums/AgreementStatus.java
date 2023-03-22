package com.project.bankproj.entity.enums;

public enum AgreementStatus {
    ACTIVE(0),
    PENDING(1),
    BLOCKED(2),
    REMOVED(3);
    private final int value;

    AgreementStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}