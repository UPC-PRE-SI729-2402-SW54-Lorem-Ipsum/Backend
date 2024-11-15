package com.loremipsum.lawconnectplatform.legalcase.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.legalcase.application.internal.outboundServices.ExternalConsultationLegalCaseService;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetAllLegalCasesQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetLegalCaseByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetLegalCaseByIdQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseQueryService;
import com.loremipsum.lawconnectplatform.legalcase.infrastructure.persistence.jpa.repositories.LegalCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalCaseQueryServiceImpl implements LegalCaseQueryService {

    private final LegalCaseRepository legalCaseRepository;
    private final ExternalConsultationLegalCaseService externalConsultationLegalCaseService;

    public LegalCaseQueryServiceImpl(LegalCaseRepository legalCaseRepository, ExternalConsultationLegalCaseService externalConsultationLegalCaseService) {
        this.legalCaseRepository = legalCaseRepository;
        this.externalConsultationLegalCaseService = externalConsultationLegalCaseService;
    }

    @Override
    public List<LegalCase> handle(GetAllLegalCasesQuery query) {
        return legalCaseRepository.findAll();
    }

    @Override
    public Optional<LegalCase> handle(GetLegalCaseByIdQuery query) {
        return legalCaseRepository.findById(query.legalCaseId());
    }

    @Override
    public Optional<LegalCase> handle(GetLegalCaseByConsultationIdQuery query) {
        var consultation = externalConsultationLegalCaseService.getConsultationById(query.consultationId());
        return legalCaseRepository.findByConsultation(consultation.get());
    }
}
