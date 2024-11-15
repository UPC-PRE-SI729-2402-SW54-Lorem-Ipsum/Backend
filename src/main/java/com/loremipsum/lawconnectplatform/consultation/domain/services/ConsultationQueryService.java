package com.loremipsum.lawconnectplatform.consultation.domain.services;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ConsultationQueryService {
    List<Consultation> handle(GetAllConsultationsByLawyerIdQuery query);
    List<Consultation> handle(GetAllConsultationsByPaymentIdQuery query);
    Optional<Consultation> handle(GetConsultationByIdQuery query);
    Optional<Consultation> handle(GetConsultationByLawyerIdAndPaymentIdQuery query);
    Optional<Consultation> handle(GetConsultationByPaymentIdQuery query);
    Optional<Long> handle(GetPaymentIdByConsultationIdQuery query);
}
