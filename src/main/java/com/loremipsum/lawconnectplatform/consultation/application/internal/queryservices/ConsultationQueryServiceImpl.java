package com.loremipsum.lawconnectplatform.consultation.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllConsultationsByLawyerIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetConsultationByIdQuery;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationQueryService;
import com.loremipsum.lawconnectplatform.consultation.infrastructure.persistence.jpa.repositories.ConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationQueryServiceImpl implements ConsultationQueryService {
    private final ConsultationRepository consultationRepository;

    public ConsultationQueryServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }


    @Override
    public Optional<Consultation> handle(GetConsultationByIdQuery query) {
        return consultationRepository.findById(query.consultationId());
    }

    @Override
    public List<Consultation> handle(GetAllConsultationsByLawyerIdQuery query) {
        return consultationRepository.findAllByLawyerId(query.lawyerId());
    }
}
