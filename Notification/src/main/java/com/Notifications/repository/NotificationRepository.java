package com.Notifications.repository;

import com.Notifications.domain.Notifikacija;
import com.Notifications.domain.TipNotifikacije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notifikacija, Long> {
    Optional<Notifikacija> findByTipNotifikacije(TipNotifikacije tipNotifikacije);

//    Optional<Notifikacija> findByEmail(String email);

    Optional<Notifikacija>findByDatumSlanja(LocalDateTime start);

    //Optional<Notifikacija>findAllByUser(Notifikacija notifikacija);

    //Optional<Notifikacija>findAllByManager(Notifikacija notifikacija);
}