package com.GDGoC.BaS.user.repository;

import com.GDGoC.BaS.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findBySocialId(String socialId);
}
