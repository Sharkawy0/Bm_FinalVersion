package com.example.Bm.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransactionHistoryDTO {
    private String transactionType;
    private String transactionStatus;
    private String transactionDate;
    private String recipientAccountId;

}
