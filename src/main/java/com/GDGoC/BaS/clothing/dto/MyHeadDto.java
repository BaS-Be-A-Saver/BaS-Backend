package com.GDGoC.BaS.clothing.dto;

import com.GDGoC.BaS.clothing.domain.Head;

public record MyHeadDto(
        String name,
        String imageUrl
) {
    public static MyHeadDto of(Head head) {
        return new MyHeadDto(
                head.getName(),
                head.getImageUrl()
        );
    }
}
