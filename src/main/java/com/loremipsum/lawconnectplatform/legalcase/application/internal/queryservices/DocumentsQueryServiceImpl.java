package com.loremipsum.lawconnectplatform.legalcase.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.entities.DocumentsItem;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetAllDocumentsByLegalCaseQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetDocumentByIdQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.DocumentsQueryService;
import com.loremipsum.lawconnectplatform.legalcase.infrastructure.persistence.jpa.repositories.DocumentsRepository;
import com.loremipsum.lawconnectplatform.legalcase.infrastructure.persistence.jpa.repositories.LegalCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentsQueryServiceImpl implements DocumentsQueryService {

    private final DocumentsRepository documentsRepository;
    private final LegalCaseRepository legalCaseRepository;

    public DocumentsQueryServiceImpl(DocumentsRepository documentsRepository, LegalCaseRepository legalCaseRepository) {
        this.documentsRepository = documentsRepository;
        this.legalCaseRepository = legalCaseRepository;
    }

    @Override
    public List<DocumentsItem> handle(GetAllDocumentsByLegalCaseQuery query) {
        var legalCase = legalCaseRepository.findById(query.legalCaseId());
        return documentsRepository.findAllByLegalCase(legalCase.get());
    }

    @Override
    public Optional<DocumentsItem> handle(GetDocumentByIdQuery query) {
        return documentsRepository.findById(query.documentId());
    }
}
