package com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.interfaces.acl.ConsultationContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalConsultationCommunicationService {

    private final ConsultationContextFacade consultationContextFacade;

    public ExternalConsultationCommunicationService(ConsultationContextFacade consultationContextFacade) {
        this.consultationContextFacade = consultationContextFacade;
    }

    public Optional<Consultation> getConsultationById(Long consultationId){
        return consultationContextFacade.getConsultationById(consultationId);
    }
}
