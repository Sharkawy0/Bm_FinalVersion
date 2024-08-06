package com.example.Bm.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Setter
public class CreateCustomerDTO {


    @NotNull
    private final String name;

    @NotNull
    @Email
    private final String email;


    @NotNull
    @Size(min = 6)
    private final String password;

}
