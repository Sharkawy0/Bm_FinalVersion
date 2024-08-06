package com.example.Bm.exception.custom;

public class TransactionHistoryNotFoundException extends RuntimeException {
    public TransactionHistoryNotFoundException(String message) {
        super(message);
    }
}
