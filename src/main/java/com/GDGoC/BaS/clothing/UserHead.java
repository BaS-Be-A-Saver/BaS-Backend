package com.GDGoC.BaS.clothing;

import com.GDGoC.BaS.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class UserHead {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userHeadId;

    @Column(nullable = false)
    private Boolean isEquipped;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "head_id", nullable = true)
    private Head head;
}
