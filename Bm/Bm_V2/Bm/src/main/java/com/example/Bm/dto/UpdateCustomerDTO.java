package com.example.Bm.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Data
@Getter
@AllArgsConstructor
public class UpdateCustomerDTO {
    private final String name;
    @Setter
    @Getter
    private final String lastName;
    @Setter
    private final String phoneNumber;


    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String setPhoneNumber(String phoneNumber) {
        return phoneNumber;
    }

    public String setLastName(String lastName) {
        return lastName;
    }




}
