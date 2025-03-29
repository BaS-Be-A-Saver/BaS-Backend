package com.GDGoC.BaS.clothing.repository;

import com.GDGoC.BaS.clothing.domain.UserAccessory;
import com.GDGoC.BaS.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessoryRepository extends JpaRepository<UserAccessory, Long> {

    Optional<UserAccessory> findByUserAndIsEquippedTrue(User user);

    List<UserAccessory> findAllByUser(User user);
}
