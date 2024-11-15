package com.loremipsum.lawconnectplatform.legalcase.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.interfaces.acl.ConsultationContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalConsultationLegalCaseService {

    private final ConsultationContextFacade consultationContextFacade;

    public ExternalConsultationLegalCaseService(ConsultationContextFacade consultationContextFacade) {
        this.consultationContextFacade = consultationContextFacade;
    }

    public Optional<Consultation> getConsultationById(Long consultationId){
        return consultationContextFacade.getConsultationById(consultationId);
    }
}
