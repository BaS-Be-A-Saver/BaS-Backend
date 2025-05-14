package com.GDGoC.BaS.booth.repository;

import com.GDGoC.BaS.booth.domain.Booth;
import com.GDGoC.BaS.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothRepository extends JpaRepository<Booth, Long> {

    Optional<Booth> findByCode(String code);

    @Query("SELECT bu.booth FROM BoothUser bu WHERE bu.user = :user")
    List<Booth> findBoothsByUser(@Param("user") User user);
}
