package com.loremipsum.lawconnectplatform.communication.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.communication.domain.model.entities.MessageItem;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllMessagesByChatRoomIdQuery;
import com.loremipsum.lawconnectplatform.communication.domain.services.MessageQueryService;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.ChatRoomRepository;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;

    public MessageQueryServiceImpl(MessageRepository messageRepository, ChatRoomRepository chatRoomRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public List<MessageItem> handle(GetAllMessagesByChatRoomIdQuery query) {
        var chatRoom = chatRoomRepository.findById(query.chatRoomId());
        return messageRepository.findAllByChatRoom(chatRoom.get());
    }
}
