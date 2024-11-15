package com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.communication.domain.model.entities.MessageItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageItem, Long> {
    List<MessageItem> findAllByChatRoom(ChatRoom chatRoom);
}
