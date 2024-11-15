package com.loremipsum.lawconnectplatform.consultation.interfaces.acl;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.ChangeConsultationStatusCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetConsultationByIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetConsultationByPaymentIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetPaymentIdByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationCommandService;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultationContextFacade {

    private final ConsultationCommandService consultationCommandService;
    private final ConsultationQueryService consultationQueryService;

    public ConsultationContextFacade(ConsultationCommandService consultationCommandService, ConsultationQueryService consultationQueryService) {
        this.consultationCommandService = consultationCommandService;
        this.consultationQueryService = consultationQueryService;
    }
    public Optional<Consultation> getConsultationById(Long consultationId){
        return consultationQueryService.handle(new GetConsultationByIdQuery(consultationId));
    }

    public void changeConsultationStatus(Long consultationId){
        consultationCommandService.handle(new ChangeConsultationStatusCommand(consultationId));
    }

    public Optional<Consultation> getConsultationByPaymentId(Long paymentId){
        return consultationQueryService.handle(new GetConsultationByPaymentIdQuery(paymentId));
    }

    public Optional<Long> getPaymentIdByConsultationId(Long consultationId){
        return consultationQueryService.handle(new GetPaymentIdByConsultationIdQuery(consultationId));
    }
}
