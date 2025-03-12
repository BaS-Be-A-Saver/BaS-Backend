package com.GDGoC.BaS.waterdrop.repository;

import com.GDGoC.BaS.user.domain.User;
import com.GDGoC.BaS.waterdrop.domain.Reason;
import com.GDGoC.BaS.waterdrop.domain.WaterdropHistory;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaterdropHistoryRepository extends JpaRepository<WaterdropHistory, Long> {

    boolean existsByUserAndReasonAndCreatedDate(User user, Reason reason, LocalDate createdDate);
}
