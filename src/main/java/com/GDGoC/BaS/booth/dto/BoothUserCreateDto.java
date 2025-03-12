package com.GDGoC.BaS.booth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record BoothUserCreateDto(
        @NotNull(message = "부스코드는 null일 수 없습니다.")
        @Pattern(regexp = "^[A-Za-z0-9]{6}$", message = "부스코드는 알파벳(A-Z, a-z)과 숫자로 이루어진 6자리여야 합니다.")
        String code
) {
    public BoothUserCreateDto {
        if (code != null) {
            code = code.toUpperCase();
        }
    }
}
