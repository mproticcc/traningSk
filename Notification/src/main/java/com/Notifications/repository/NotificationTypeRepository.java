package com.Notifications.repository;

import com.Notifications.domain.TipNotifikacije;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationTypeRepository extends JpaRepository<TipNotifikacije, Long> {

}
