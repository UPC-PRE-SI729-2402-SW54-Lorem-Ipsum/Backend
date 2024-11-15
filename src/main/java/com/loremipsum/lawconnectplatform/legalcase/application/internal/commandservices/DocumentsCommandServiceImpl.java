package com.loremipsum.lawconnectplatform.legalcase.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.legalcase.application.internal.outboundServices.ExternalConsultationLegalCaseService;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.AddDocumentByLegalCaseIdCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.ChangeDocumentStatusCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.entities.DocumentsItem;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.DocumentsCommandService;
import com.loremipsum.lawconnectplatform.legalcase.infrastructure.persistence.jpa.repositories.DocumentsRepository;
import com.loremipsum.lawconnectplatform.legalcase.infrastructure.persistence.jpa.repositories.LegalCaseRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentsCommandServiceImpl implements DocumentsCommandService {

    private final DocumentsRepository documentsRepository;
    private final LegalCaseRepository legalCaseRepository;

    public DocumentsCommandServiceImpl(DocumentsRepository documentsRepository, LegalCaseRepository legalCaseRepository) {
        this.documentsRepository = documentsRepository;
        this.legalCaseRepository = legalCaseRepository;
    }

    @Override
    public void handle(AddDocumentByLegalCaseIdCommand command) {
        var legalCase = legalCaseRepository.findById(command.legalCaseId());
        if (legalCase.isEmpty()) {
            throw new IllegalArgumentException("Legal case not found");
        }
        var document = new DocumentsItem(command, legalCase.get());

        legalCase.get().getDocuments().addDocumentItem(document);

        documentsRepository.save(document);

    }

    @Override
    public void handle(ChangeDocumentStatusCommand command) {
        var document = documentsRepository.findById(command.documentId());
        if (document.isEmpty()) {
            throw new IllegalArgumentException("Document not found");
        }
        document.get().setStatus(command.status());
        documentsRepository.save(document.get());
    }
}
