package com.project.bankproj.entity.enums;

public enum ManagerStatus {
    ACTIVE(0),
    PENDING(1),
    REMOVED(2);
    private final int value;

    ManagerStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}