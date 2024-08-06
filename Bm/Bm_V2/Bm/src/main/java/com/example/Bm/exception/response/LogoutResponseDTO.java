package com.example.Bm.exception.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutResponseDTO {
    private boolean status;
    @Setter
    private String message;

    public void setStatus(boolean success) {
        this.status = success;
    }

}