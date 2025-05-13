package com.GDGoC.BaS.booth.dto;

public record BoothUserDto(
        String nickname,
        Integer goal,
        Integer duration,
        Long successCount,
        String skinImage,
        String eyesImage,
        String noseImage,
        String mouthImage,
        String headImage,
        String towelImage,
        String accessoryImage
) {
}
