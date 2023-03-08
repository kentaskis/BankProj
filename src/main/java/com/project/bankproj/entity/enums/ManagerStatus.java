package com.project.bankproj.entity.enums;

public enum ManagerStatus {
    ACTIVE(1),
    PENDING(2),
    REMOVED(3);
    private final int value;

    ManagerStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}