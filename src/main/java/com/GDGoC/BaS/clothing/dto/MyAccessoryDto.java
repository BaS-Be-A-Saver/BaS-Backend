package com.GDGoC.BaS.clothing.dto;

import com.GDGoC.BaS.clothing.domain.Accessory;

public record MyAccessoryDto(
        String name,
        String imageUrl
) {
    public static MyAccessoryDto of(Accessory accessory) {
        return new MyAccessoryDto(
                accessory.getName(),
                accessory.getImageUrl()
        );
    }
}
