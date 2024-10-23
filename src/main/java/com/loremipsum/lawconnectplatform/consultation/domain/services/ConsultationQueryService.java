package com.loremipsum.lawconnectplatform.consultation.domain.services;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllConsultationsByLawyerIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetConsultationByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ConsultationQueryService {
    Optional<Consultation> handle(GetConsultationByIdQuery query);
    List<Consultation> handle(GetAllConsultationsByLawyerIdQuery query);
}
