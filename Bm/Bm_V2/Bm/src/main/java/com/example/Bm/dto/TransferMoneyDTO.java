package com.example.Bm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransferMoneyDTO {
    private Long id;
    private String senderAccountId;
    private String recipientAccountId;
    private double amount;
    private String transferDate;
    private String transferStatus;

}
