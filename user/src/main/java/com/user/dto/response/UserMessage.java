package com.user.dto.response;
import lombok.Data;

@Data
public class UserMessage {

    private Long authId;
    private String action;

    public Long getAuthId() {
        return authId;
    }

    public String getAction() {
        return action;
    }
}