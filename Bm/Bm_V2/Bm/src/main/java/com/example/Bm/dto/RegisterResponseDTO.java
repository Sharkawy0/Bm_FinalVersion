package com.example.Bm.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponseDTO {

    private String message;

    public void setSuccess(boolean b) {
    }

    public void setCustomer(CustomerDTO customerDTO) {
    }
}
