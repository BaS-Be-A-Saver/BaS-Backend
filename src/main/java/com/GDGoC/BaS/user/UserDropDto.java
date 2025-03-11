package com.GDGoC.BaS.user;

public record UserDropDto(
        Integer waterdrop
) {
    static public UserDropDto of(Integer waterdrop) {
        return new UserDropDto(waterdrop);
    }
}
