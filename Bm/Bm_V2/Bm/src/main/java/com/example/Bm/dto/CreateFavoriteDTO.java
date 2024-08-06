package com.example.Bm.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class CreateFavoriteDTO {

    @NotBlank(message = "Recipient name is mandatory")
    private String recipientName;

    @NotBlank(message = "Recipient account ID is mandatory")
    @Pattern(regexp = "\\d{16}", message = "Recipient account ID must be 16 digits")
    private String recipientAccountId;

    // Getters and setters
    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAccountId() {
        return recipientAccountId;
    }

    public void setRecipientAccountId(String recipientAccountId) {
        this.recipientAccountId = recipientAccountId;
    }
}
