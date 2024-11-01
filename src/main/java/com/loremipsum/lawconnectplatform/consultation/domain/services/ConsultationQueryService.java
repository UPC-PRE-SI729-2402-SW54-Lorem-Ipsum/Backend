package com.loremipsum.lawconnectplatform.consultation.domain.services;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllConsultationsByLawyerIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllConsultationsByPaymentIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetConsultationByIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetConsultationByLawyerIdAndPaymentIdQuery;

import java.util.List;
import java.util.Optional;

public interface ConsultationQueryService {
    List<Consultation> handle(GetAllConsultationsByLawyerIdQuery query);
    List<Consultation> handle(GetAllConsultationsByPaymentIdQuery query);
    Optional<Consultation> handle(GetConsultationByIdQuery query);
    Optional<Consultation> handle(GetConsultationByLawyerIdAndPaymentIdQuery query);

}
