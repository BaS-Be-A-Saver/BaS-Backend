package com.GDGoC.BaS.clothing.repository;

import com.GDGoC.BaS.clothing.domain.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository<Accessory, Long> {
}
