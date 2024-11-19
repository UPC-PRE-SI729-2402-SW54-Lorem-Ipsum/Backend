package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources.LegalCaseResource;

public class LegalCaseResourceFromEntityAssembler {
    public static LegalCaseResource toEntityFromResource(LegalCase entity, ConsultationResource consultationResource) {
        return new LegalCaseResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus().name(),
                consultationResource,
                entity.getDocuments().getDocumentsItems().stream().map(DocumentsItemResourceFromEntityAssembler::toEntityFromResource).toList()
        );
    }
}
