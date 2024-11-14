package com.loremipsum.lawconnectplatform.followup.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.followup.domain.model.aggregates.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByLegalCaseId(Long legalCaseId);
    List<Notification> findAllByClientId(Long clientId);
}
