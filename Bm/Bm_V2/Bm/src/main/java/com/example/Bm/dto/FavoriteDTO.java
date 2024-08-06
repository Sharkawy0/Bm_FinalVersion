package com.example.Bm.dto;

public class FavoriteDTO {

    private Long id;
    private String recipientName;
    private String recipientAccountId;

    public FavoriteDTO(Long id, String recipientName, String recipientAccountId) {
        this.id = id;
        this.recipientName = recipientName;
        this.recipientAccountId = recipientAccountId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
