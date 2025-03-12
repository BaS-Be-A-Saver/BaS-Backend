package com.GDGoC.BaS.notification.service;

import com.GDGoC.BaS.notification.repository.NotificationRepository;
import com.GDGoC.BaS.notification.dto.NotificationDto;
import com.GDGoC.BaS.notification.dto.NotificationListDto;
import com.GDGoC.BaS.user.domain.User;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Transactional(readOnly = true)
    public NotificationListDto getNotifications(User user) {
        List<NotificationDto> notificationDtos = notificationRepository
                .findByIsGlobalTrueOrUserOrderByCreatedDateDesc(user)
                .stream()
                .map(NotificationDto::from)
                .collect(Collectors.toList());
        return NotificationListDto.of(notificationDtos);
    }
}
