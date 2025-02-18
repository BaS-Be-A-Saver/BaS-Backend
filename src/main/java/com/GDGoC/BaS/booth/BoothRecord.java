package com.GDGoC.BaS.booth;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class BoothRecord {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long boothRecordId;

    @Column(nullable = false)
    private Integer totalCount;

    @Column(nullable = false)
    private Integer successCount;

    @Column(nullable = false)
    private Integer averageRecord;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "booth_id", nullable = true)
    private Booth booth;
}
