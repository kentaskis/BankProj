package com.project.bankproj.entity.enums;

public enum ClientStatus {
    ACTIVE(0),
    PENDING(1),
    REMOVED(2),
    BLOCKED(3);
    private final int value;
    ClientStatus(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}