package com.GDGoC.BaS.booth.repository;

import com.GDGoC.BaS.booth.domain.Booth;
import com.GDGoC.BaS.booth.domain.BoothUser;
import com.GDGoC.BaS.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoothUserRepository extends JpaRepository<BoothUser, Long> {

    boolean existsByUserAndBooth(User user, Booth booth);

    Optional<BoothUser> findByUserAndBooth(User user, Booth booth);

    long countByBooth(Booth booth);
}
