package com.GDGoC.BaS.shower;

import com.GDGoC.BaS.user.User;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepository extends JpaRepository<UserRecord, Long> {

    Optional<UserRecord> findByUserAndCreatedDate(User user, LocalDate createdDate);
}
