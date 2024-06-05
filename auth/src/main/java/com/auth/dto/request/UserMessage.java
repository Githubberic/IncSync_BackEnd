package com.auth.dto.request;
import lombok.Data;

@Data
public class UserMessage {

    private Long authId;
    private String action;

    public UserMessage(Long authId, String action) {
        this.authId = authId;
        this.action = action;
    }
}