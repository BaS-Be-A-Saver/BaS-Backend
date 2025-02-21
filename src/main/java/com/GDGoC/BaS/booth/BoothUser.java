package com.GDGoC.BaS.booth;

import com.GDGoC.BaS.user.User;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class BoothUser {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long boothUserId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "booth_id", nullable = true)
    private Booth booth;
}
