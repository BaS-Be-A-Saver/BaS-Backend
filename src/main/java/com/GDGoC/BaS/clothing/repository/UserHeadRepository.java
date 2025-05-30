package com.GDGoC.BaS.clothing.repository;

import com.GDGoC.BaS.clothing.domain.UserHead;
import com.GDGoC.BaS.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHeadRepository extends JpaRepository<UserHead, Long> {

    Optional<UserHead> findByUserAndIsEquippedTrue(User user);

    List<UserHead> findAllByUser(User user);

    @Query("SELECT uh.head.headId FROM UserHead uh WHERE uh.user = :user")
    List<Byte> findBoughtHeadIdsByUser(@Param("user") User user);
}
