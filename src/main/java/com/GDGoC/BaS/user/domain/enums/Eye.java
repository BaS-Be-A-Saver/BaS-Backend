package com.GDGoC.BaS.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Eye {
    BASIC("basicImage"),
    PLAYFUL("playfulImage"),
    STICK("stickImage");

    private final String image;
}
