package com.GDGoC.BaS.notification;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Notification {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long notificationId;

    @CreatedDate
    @Column(nullable = false)
    protected LocalDateTime createdDate;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private Long userId;
}
