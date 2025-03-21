package com.GDGoC.BaS.notification.controller;

import static org.springframework.http.HttpStatus.OK;

import com.GDGoC.BaS.notification.service.NotificationService;
import com.GDGoC.BaS.notification.dto.NotificationListDto;
import com.GDGoC.BaS.user.domain.User;
import com.GDGoC.BaS.user.service.UserService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<NotificationListDto> getNotifications(Principal principal) {
        User user = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(notificationService.getNotifications(user));
    }
}
