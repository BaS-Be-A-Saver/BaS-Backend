package com.GDGoC.BaS.shower.dto;

import jakarta.validation.constraints.NotNull;

public record UserRecordCreateDto(
        @NotNull(message = "샤워시간은 null일 수 없습니다.")
        Integer duration
) {
}
