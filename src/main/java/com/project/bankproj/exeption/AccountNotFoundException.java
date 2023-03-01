package com.project.bankproj.exeption;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException (String message) {
        super(message);
    }
}