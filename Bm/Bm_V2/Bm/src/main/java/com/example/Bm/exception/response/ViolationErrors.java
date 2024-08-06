package com.example.Bm.exception.response;

import lombok.Builder;

@Builder
public record ViolationErrors(String fieldName, String message) {
}
