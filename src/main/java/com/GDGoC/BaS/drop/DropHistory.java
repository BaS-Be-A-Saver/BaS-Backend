package com.GDGoC.BaS.drop;

import com.GDGoC.BaS.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = PROTECTED)
public class DropHistory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long dropHistoryId;

    @CreatedDate
    @Column(nullable = false)
    protected LocalDate createdDate;

    @Column(nullable = false)
    private Integer amount;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Reason reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
