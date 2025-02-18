package com.GDGoC.BaS.notification;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long notificationId;

    @CreatedDate
    @Column(nullable = false)
    protected LocalDate createdDate;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    private Long userId;
}
