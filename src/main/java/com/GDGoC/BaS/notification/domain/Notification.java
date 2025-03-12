package com.GDGoC.BaS.notification.domain;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.GDGoC.BaS.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
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
    private Boolean isGlobal;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;
}
