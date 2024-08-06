package com.example.Bm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private String name;
    private  String email;

    private  String phoneNumber;

    public void setMessage(String registerSuccessful) {
    }


//    private  AccountDTO account;

}
