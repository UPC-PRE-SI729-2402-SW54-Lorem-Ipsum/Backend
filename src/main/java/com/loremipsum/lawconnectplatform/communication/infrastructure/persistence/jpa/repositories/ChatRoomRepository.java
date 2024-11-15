package com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByConsultation(Consultation consultation);
}
