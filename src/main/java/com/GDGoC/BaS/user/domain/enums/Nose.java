package com.GDGoC.BaS.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Nose {
    BASIC("basicImage"),
    SHARP("sharpImage"),
    STICK("stickImage");

    private final String image;
}
