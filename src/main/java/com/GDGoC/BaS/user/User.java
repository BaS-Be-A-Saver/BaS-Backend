package com.GDGoC.BaS.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 600")
    @Min(1)
    @Max(1200)
    private Integer goal;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    @Min(0)
    private Integer drop;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20, columnDefinition = "VARCHAR(20) DEFAULT 'LIGHT'")
    private Skin skin;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Eye eye;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Nose nose;

    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Mouth mouth;
}
