package com.GDGoC.BaS.clothing.dto;

import com.GDGoC.BaS.clothing.domain.Towel;

public record StoreTowelDto(
        String name,
        String imageUrl,
        Byte price,
        String description,
        Boolean hasBought
) {
    public static StoreTowelDto of(Towel towel, Boolean hasBought) {
        return new StoreTowelDto(
                towel.getName(),
                towel.getImageUrl(),
                towel.getPrice(),
                towel.getDescription(),
                hasBought
        );
    }
}
