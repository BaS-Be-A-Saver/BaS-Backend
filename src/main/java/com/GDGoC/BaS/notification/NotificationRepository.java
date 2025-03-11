package com.GDGoC.BaS.notification;

import com.GDGoC.BaS.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByIsGlobalTrueOrUserOrderByCreatedDateDesc(User user);
}
