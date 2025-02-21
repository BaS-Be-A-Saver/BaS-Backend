package com.GDGoC.BaS.shower;

import com.GDGoC.BaS.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = PROTECTED)
public class UserRecord {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userRecordId;

    @CreatedDate
    @Column(nullable = false)
    protected LocalDate createdDate;

    @Column(nullable = false)
    private Integer goal;

    @Column(nullable = false)
    private Integer duration;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
