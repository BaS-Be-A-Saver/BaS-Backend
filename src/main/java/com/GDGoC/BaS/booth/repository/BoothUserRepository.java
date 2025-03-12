package com.GDGoC.BaS.booth.repository;

import com.GDGoC.BaS.booth.domain.BoothUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothUserRepository extends JpaRepository<BoothUser, Long> {
}
