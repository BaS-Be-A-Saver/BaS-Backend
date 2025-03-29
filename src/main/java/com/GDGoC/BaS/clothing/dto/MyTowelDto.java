package com.GDGoC.BaS.clothing.dto;

import com.GDGoC.BaS.clothing.domain.Towel;

public record MyTowelDto(
        String name,
        String imageUrl
) {
    public static MyTowelDto of(Towel towel) {
        return new MyTowelDto(
                towel.getName(),
                towel.getImageUrl()
        );
    }
}
