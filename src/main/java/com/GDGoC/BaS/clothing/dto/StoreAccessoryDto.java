package com.GDGoC.BaS.clothing.dto;

import com.GDGoC.BaS.clothing.domain.Accessory;

public record StoreAccessoryDto(
        String name,
        String imageUrl,
        Byte price,
        String description,
        Boolean hasBought
) {
    public static StoreAccessoryDto of(Accessory accessory, Boolean hasBought) {
        return new StoreAccessoryDto(
                accessory.getName(),
                accessory.getImageUrl(),
                accessory.getPrice(),
                accessory.getDescription(),
                hasBought
        );
    }
}
