package com.GDGoC.BaS.user.dto;

public record LoginResponse(
        String authorization
) {
    public static LoginResponse of(String token) {
        return new LoginResponse(token);
    }
}
