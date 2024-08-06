package com.example.Bm.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ViewCustomerDTO {
    private final String name;
    private final String email;

    private LocalDateTime timestamp;}
