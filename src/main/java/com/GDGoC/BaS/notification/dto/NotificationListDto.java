package com.GDGoC.BaS.notification.dto;

import java.util.List;

public record NotificationListDto(List<NotificationDto> notifications) {
    static public NotificationListDto of(List<NotificationDto> notifications) {
        return new NotificationListDto(notifications);
    }
}
