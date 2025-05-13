package com.GDGoC.BaS.booth.dto;

import java.util.List;

public record BoothDetailDto(
        String name,
        Integer averageDuration,
        String code,
        List<BoothUserDto> users
) {
}
