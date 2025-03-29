package com.GDGoC.BaS.clothing.repository;

import com.GDGoC.BaS.clothing.domain.UserTowel;
import com.GDGoC.BaS.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTowelRepository extends JpaRepository<UserTowel, Long> {

    Optional<UserTowel> findByUserAndIsEquippedTrue(User user);

    List<UserTowel> findAllByUser(User user);

    @Query("SELECT ut.towel.towelId FROM UserTowel ut WHERE ut.user = :user")
    List<Byte> findBoughtTowelIdsByUser(@Param("user") User user);
}
