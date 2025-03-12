package com.GDGoC.BaS.booth;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.GDGoC.BaS.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.NoArgsConstructor;

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

    @Builder
    public BoothUser(User user, Booth booth) {
        this.user = user;
        this.booth = booth;
    }
}
