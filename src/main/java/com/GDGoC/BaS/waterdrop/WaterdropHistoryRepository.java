package com.GDGoC.BaS.waterdrop;

import com.GDGoC.BaS.user.User;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterdropHistoryRepository extends JpaRepository<WaterdropHistory, Long> {

    boolean existsByUserAndReasonAndCreatedDate(User user, Reason reason, LocalDate createdDate);
}
