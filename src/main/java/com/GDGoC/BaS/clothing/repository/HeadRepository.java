package com.GDGoC.BaS.clothing.repository;

import com.GDGoC.BaS.clothing.domain.Head;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadRepository extends JpaRepository<Head, Long> {
}
