package com.GDGoC.BaS.clothing.repository;

import com.GDGoC.BaS.clothing.domain.UserHead;
import com.GDGoC.BaS.clothing.domain.UserTowel;
import com.GDGoC.BaS.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTowelRepository extends JpaRepository<UserTowel, Long> {

    Optional<UserTowel> findByUserAndIsEquippedTrue(User user);
}
