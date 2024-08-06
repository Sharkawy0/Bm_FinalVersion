package com.example.Bm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionHistoryDTO {
    private Long id;
    private String transactionType;
    private String transactionStatus;
    private String transactionDate;
    private String recipientAccountId;

}

