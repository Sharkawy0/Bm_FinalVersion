package com.example.Bm.exception.custom;

public class SessionTimeoutException extends RuntimeException {
    public SessionTimeoutException(String message) {
        super(message);
    }
}
