package com.example.Bm.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateTransferMoneyDTO {

    @NotNull(message = "Sender account ID is required")
    @Pattern(regexp = "\\d{16}", message = "Sender account ID must be 16 digits")
    private String senderAccountId;

    @NotNull(message = "Recipient account ID is required")
    @Pattern(regexp = "\\d{16}", message = "Recipient account ID must be 16 digits")
    private String recipientAccountId;

    @NotNull(message = "Amount is required")
    private double amount;

    public String getTransferDate() {
        return null;
    }

    public String getTransferStatus() {
        return String.valueOf(HttpStatus.OK);
    }

}
