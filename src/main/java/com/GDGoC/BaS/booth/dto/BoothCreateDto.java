package com.GDGoC.BaS.booth.dto;

import jakarta.validation.constraints.NotNull;

public record BoothCreateDto(
        @NotNull(message = "부스명은 null일 수 없습니다.")
        String name
) {
}
