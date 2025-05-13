package com.GDGoC.BaS.booth.domain;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Booth {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long boothId;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 6, unique = true)
    private String code;

    @OneToMany(mappedBy = "booth", cascade = ALL, orphanRemoval = true)
    private List<BoothUser> boothUsers = new ArrayList<>();

    @OneToMany(mappedBy = "booth", cascade = ALL, orphanRemoval = true)
    private List<BoothRecord> boothRecords = new ArrayList<>();

    @Builder
    public Booth(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
