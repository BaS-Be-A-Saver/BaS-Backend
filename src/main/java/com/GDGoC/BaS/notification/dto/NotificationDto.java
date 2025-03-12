package com.GDGoC.BaS.notification.dto;

import com.GDGoC.BaS.notification.domain.Notification;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public record NotificationDto(
        String body,
        String createdDate
) {
    public static NotificationDto from(Notification notification) {
        LocalDate createdDateValue = notification.getCreatedDate();
        long daysDifference = ChronoUnit.DAYS.between(createdDateValue, LocalDate.now());

        String formattedDate;
        if (daysDifference == 0) {
            formattedDate = "방금 전";
        } else if (daysDifference >= 1 && daysDifference < 7) {
            formattedDate = daysDifference + "일 전";
        } else if (daysDifference == 7) {
            formattedDate = "일주일 전";
        } else {
            formattedDate = createdDateValue.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        }
        return new NotificationDto(
                notification.getBody(),
                formattedDate
        );
    }
}
