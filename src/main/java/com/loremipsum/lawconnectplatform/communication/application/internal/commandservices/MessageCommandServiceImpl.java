package com.loremipsum.lawconnectplatform.communication.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.AddMessageByChatRoomIdCommand;
import com.loremipsum.lawconnectplatform.communication.domain.model.entities.MessageItem;
import com.loremipsum.lawconnectplatform.communication.domain.services.MessageCommandService;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.ChatRoomRepository;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {

    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;

    public MessageCommandServiceImpl(ChatRoomRepository chatRoomRepository, MessageRepository messageRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void handle(AddMessageByChatRoomIdCommand command) {

        var chatRoom = chatRoomRepository.findById(command.chatRoomId());

        if (chatRoom.isEmpty()) {
            throw new IllegalArgumentException("Chat room not found");
        }

        var message = new MessageItem(command, chatRoom.get());

        chatRoom.get().getMessages().addMessage(message);

        messageRepository.save(message);

    }
}
