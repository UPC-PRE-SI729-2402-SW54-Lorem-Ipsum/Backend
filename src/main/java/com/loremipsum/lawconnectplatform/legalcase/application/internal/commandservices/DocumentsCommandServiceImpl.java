package com.loremipsum.lawconnectplatform.legalcase.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.legalcase.application.internal.outboundServices.ExternalConsultationLegalCaseService;
import com.loremipsum.lawconnectplatform.legalcase.application.internal.outboundServices.ExternalFollowUpLegalCaseService;
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
    private final ExternalFollowUpLegalCaseService externalFollowUpLegalCaseService;
    private final ExternalConsultationLegalCaseService externalConsultationLegalCaseService;

    public DocumentsCommandServiceImpl(DocumentsRepository documentsRepository, LegalCaseRepository legalCaseRepository, ExternalFollowUpLegalCaseService externalFollowUpLegalCaseService, ExternalConsultationLegalCaseService externalConsultationLegalCaseService) {
        this.documentsRepository = documentsRepository;
        this.legalCaseRepository = legalCaseRepository;
        this.externalFollowUpLegalCaseService = externalFollowUpLegalCaseService;
        this.externalConsultationLegalCaseService = externalConsultationLegalCaseService;
    }

    @Override
    public void handle(AddDocumentByLegalCaseIdCommand command) {
        var legalCase = legalCaseRepository.findById(command.legalCaseId());

        if (legalCase.isEmpty()) {
            throw new IllegalArgumentException("Legal case not found");
        }
        var document = new DocumentsItem(command, legalCase.get());

        var consultation = externalConsultationLegalCaseService.getConsultationById(legalCase.get().getConsultation().getId());

        legalCase.get().getDocuments().addDocumentItem(document);

        externalFollowUpLegalCaseService.createNotification(
                "Document added to legal case",
                "Document " + document.getTitle() +
                        "\n added to legal case " + document.getDescription() +
                        "\n ype: " + document.getType() +
                        "\n Status: " + document.getStatus(),
                        consultation.get().getClientId(),
                        consultation.get().getId()
        );

        documentsRepository.save(document);

    }

    @Override
    public void handle(ChangeDocumentStatusCommand command) {
        var document = documentsRepository.findById(command.documentId());
        if (document.isEmpty()) {
            throw new IllegalArgumentException("Document not found");
        }
        document.get().setStatus(command.status());

        var legalCase = legalCaseRepository.findById(document.get().getLegalCase().getId());
        if (legalCase.isEmpty()) {
            throw new IllegalArgumentException("Legal case not found");
        }

        var consultation = externalConsultationLegalCaseService.getConsultationById(legalCase.get().getConsultation().getId());

        externalFollowUpLegalCaseService.createNotification(
                "Document status changed",
                "Document " + document.get().getTitle() +
                        "\n status changed to " + document.get().getStatus(),
                        consultation.get().getClientId(),
                        consultation.get().getId()
        );

        documentsRepository.save(document.get());
    }
}
