package com.GDGoC.BaS.user.dto;

public record UserDropDto(
        Integer waterdrop
) {
    static public UserDropDto of(Integer waterdrop) {
        return new UserDropDto(waterdrop);
    }
}
