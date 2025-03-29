package com.GDGoC.BaS.clothing.repository;

import com.GDGoC.BaS.clothing.domain.Towel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TowelRepository extends JpaRepository<Towel, Long> {
}
