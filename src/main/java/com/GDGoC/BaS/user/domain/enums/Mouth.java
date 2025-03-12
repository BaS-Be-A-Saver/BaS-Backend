package com.GDGoC.BaS.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Mouth {
    BASIC("basicImage"),
    STICK("stickImage"),
    LAUGHING("laughingImage"),
    WIGGLE("wiggleImage");

    private final String image;
}
