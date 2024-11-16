package com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.communication.interfaces.acl.CommunicationContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalCommunicationConsultationService {

    private final CommunicationContextFacade communicationContextFacade;

    public ExternalCommunicationConsultationService(CommunicationContextFacade communicationContextFacade) {
        this.communicationContextFacade = communicationContextFacade;
    }

    public void createChatRoom(
            Long consultationId
    ){
        communicationContextFacade.createChatRoom(
                consultationId
            );
    }

    public void deleteChatRoom(
            Long consultationId
    ){
        communicationContextFacade.deleteChatRoom(
                consultationId
            );
    }
}
