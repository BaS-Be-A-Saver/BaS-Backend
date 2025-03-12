package com.GDGoC.BaS.clothing.domain;

import com.GDGoC.BaS.user.domain.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class UserAccessory {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userAccessoryId;

    @Column(nullable = false)
    private Boolean isEquipped;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "accessory_id", nullable = true)
    private Accessory accessory;
}
