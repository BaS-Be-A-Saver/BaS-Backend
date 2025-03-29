package com.GDGoC.BaS.clothing.dto;

import com.GDGoC.BaS.clothing.domain.Head;

public record StoreHeadDto(
        String name,
        String imageUrl,
        Byte price,
        String description,
        Boolean hasBought
) {
    public static StoreHeadDto of(Head head, Boolean hasBought) {
        return new StoreHeadDto(
                head.getName(),
                head.getImageUrl(),
                head.getPrice(),
                head.getDescription(),
                hasBought
        );
    }
}
