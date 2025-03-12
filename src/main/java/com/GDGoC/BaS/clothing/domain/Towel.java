package com.GDGoC.BaS.clothing.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Towel {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Byte towelId;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 500)
    private String imageUrl;

    @Column(nullable = false)
    private Byte price;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "towel", cascade = ALL, orphanRemoval = true)
    private List<UserTowel> userTowels = new ArrayList<>();
}
