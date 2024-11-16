package com.loremipsum.lawconnectplatform.communication.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateChatRoomCommand;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.DeleteChatRoomCommand;
import com.loremipsum.lawconnectplatform.communication.domain.services.ChatRoomCommandService;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomCommandServiceImpl implements ChatRoomCommandService {

    private final ChatRoomRepository chatRoomRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;

    public ChatRoomCommandServiceImpl(ChatRoomRepository chatRoomRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.chatRoomRepository = chatRoomRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @Override
    public Optional<ChatRoom> handle(CreateChatRoomCommand command) {

        var consultation = externalConsultationCommunicationService.getConsultationById(command.consultationId());

        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        var chatRoom = new ChatRoom(consultation.get());

        chatRoomRepository.save(chatRoom);

        return Optional.of(chatRoom);
    }

    @Override
    public void handle(DeleteChatRoomCommand command) {
        var consultation = externalConsultationCommunicationService.getConsultationById(command.chatRoomId());
        var chatRoom = chatRoomRepository.findByConsultation(consultation.get());

        if (chatRoom.isEmpty()) {
            throw new IllegalArgumentException("Chat room not found");
        }

        chatRoomRepository.delete(chatRoom.get());
    }
}
