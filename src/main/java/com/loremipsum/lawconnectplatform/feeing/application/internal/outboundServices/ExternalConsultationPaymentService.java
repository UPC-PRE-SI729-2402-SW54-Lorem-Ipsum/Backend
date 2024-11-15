package com.loremipsum.lawconnectplatform.feeing.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.interfaces.acl.ConsultationContextFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalConsultationPaymentService {

    private final ConsultationContextFacade consultationContextFacade;

    public ExternalConsultationPaymentService(ConsultationContextFacade consultationContextFacade) {
        this.consultationContextFacade = consultationContextFacade;
    }

    public void changeConsultationStatus(Long consultationId) {
        consultationContextFacade.changeConsultationStatus(consultationId);
    }

    public Optional<Consultation> getConsultationByPaymentId(Long paymentId) {
        return consultationContextFacade.getConsultationByPaymentId(paymentId);
    }

    public Optional<Long> getPaymentIdByConsultationId(Long consultationId) {
        return consultationContextFacade.getPaymentIdByConsultationId(consultationId);
    }
}
