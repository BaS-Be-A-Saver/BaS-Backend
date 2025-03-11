package com.GDGoC.BaS.user;

public record LoginResponse(
        String authorization
) {
    public static LoginResponse of(String token) {
        return new LoginResponse(token);
    }
}
