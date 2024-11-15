package com.loremipsum.lawconnectplatform.communication.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetChatRoomByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.communication.domain.services.ChatRoomQueryService;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.ChatRoomRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomQueryServiceImpl implements ChatRoomQueryService {

    private final ChatRoomRepository chatRoomRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;

    public ChatRoomQueryServiceImpl(ChatRoomRepository chatRoomRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.chatRoomRepository = chatRoomRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @Override
    public Optional<ChatRoom> handle(GetChatRoomByConsultationIdQuery query) {
        var consultation = externalConsultationCommunicationService.getConsultationById(query.consultationId());
        return chatRoomRepository.findByConsultation(consultation.get());
    }
}
