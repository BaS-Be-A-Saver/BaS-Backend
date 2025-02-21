package com.GDGoC.BaS.clothing;

import com.GDGoC.BaS.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class UserTowel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userTowelId;

    @Column(nullable = false)
    private Boolean isEquipped;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "towel_id", nullable = true)
    private Towel towel;
}
