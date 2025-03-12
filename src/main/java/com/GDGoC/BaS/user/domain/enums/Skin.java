package com.GDGoC.BaS.user.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Skin {
    FAIR("fairImage"),
    LIGHT("lightImage"),
    MEDIUM("mediumImage"),
    DARK("darkImage");

    private final String image;
}
