package com.GDGoC.BaS.booth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothUserRepository extends JpaRepository<BoothUser, Long> {
}
