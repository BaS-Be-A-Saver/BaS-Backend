package com.GDGoC.BaS.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record UserUpdateDto(
        @Size(min = 2, max = 10, message = "닉네임은 2~10자 사이여야 합니다")
        String nickname,

        @Min(value = 0, message = "목표는 0 이상이어야 합니다")
        @Max(value = 1200, message = "목표는 20분 이하여야 합니다")
        Integer goal,

        String skin,
        String eyes,
        String nose,
        String mouth
) {
}
