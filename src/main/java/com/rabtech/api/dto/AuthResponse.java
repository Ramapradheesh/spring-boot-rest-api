package com.rabtech.api.dto;

public class AuthResponse {

    private String token;
    private String tokenType;
    private String username;

    public AuthResponse(String token, String username) {
        this.token = token;
        this.username = username;
        this.tokenType = "Bearer";
    }

    public String getToken() {
        return token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public String getUsername() {
        return username;
    }
}