package com.example.authenticate.dto;

public class JwtResponse {
    private String accessToken;
    private String refreshToken;

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JwtResponse(String accessToken, String refreshToken, String message){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.message= message;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
