package com.GDGoC.BaS.booth.repository;

import com.GDGoC.BaS.booth.domain.Booth;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothRepository extends JpaRepository<Booth, Long> {

    Optional<Booth> findByCode(String code);
}
