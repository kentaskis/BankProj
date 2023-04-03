package com.project.bankproj.exeption;

public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }
}