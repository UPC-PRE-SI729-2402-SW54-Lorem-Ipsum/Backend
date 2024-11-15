package com.loremipsum.lawconnectplatform.consultation.application.internal.eventHandlers;

import com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices.ExternalCommunicationConsultationService;
import com.loremipsum.lawconnectplatform.consultation.domain.model.events.CreateChatRoomEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CreateChatRoomEventHandler {

    private final ExternalCommunicationConsultationService externalCommunicationConsultationService;

    public CreateChatRoomEventHandler(ExternalCommunicationConsultationService externalCommunicationConsultationService) {
        this.externalCommunicationConsultationService = externalCommunicationConsultationService;
    }

    @EventListener(CreateChatRoomEvent.class)
    public void onChatRoomCreated(CreateChatRoomEvent event) {
        System.out.println("hola");
        System.out.println("Chat room created for consultation: " + event.getConsultationId());
        externalCommunicationConsultationService.createChatRoom(
                event.getConsultationId()
            );
    }
}
