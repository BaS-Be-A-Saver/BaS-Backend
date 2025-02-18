package com.GDGoC.BaS.booth;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Booth {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long boothId;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 6, unique = true)
    private String code;

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<BoothUser> boothUsers = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = ALL, orphanRemoval = true)
    private List<BoothRecord> boothRecords = new ArrayList<>();
}
