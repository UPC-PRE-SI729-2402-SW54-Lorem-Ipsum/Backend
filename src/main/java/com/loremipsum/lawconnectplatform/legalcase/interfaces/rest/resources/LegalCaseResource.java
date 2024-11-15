package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.entities.DocumentsItem;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.valueobjects.Documents;

import java.util.List;

public record LegalCaseResource(
        Long id,
        String title,
        String description,
        String status,
        Consultation consultationId,
        List<DocumentsItemResource> documents
) {
}
