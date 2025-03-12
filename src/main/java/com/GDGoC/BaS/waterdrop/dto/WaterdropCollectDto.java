package com.GDGoC.BaS.waterdrop.dto;

import com.GDGoC.BaS.waterdrop.domain.Reason;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record WaterdropCollectDto(
        @Min(1)
        @NotNull(message = "수량은 null일 수 없습니다.")
        Integer amount,

        @NotNull(message = "지급이유는 null일 수 없습니다.")
        Reason reason
) {
}
