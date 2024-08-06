package com.example.Bm.exception.custom;

public class CustomerAlreadyExistsException extends Exception {
    public CustomerAlreadyExistsException() {
        super();
    }
    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
