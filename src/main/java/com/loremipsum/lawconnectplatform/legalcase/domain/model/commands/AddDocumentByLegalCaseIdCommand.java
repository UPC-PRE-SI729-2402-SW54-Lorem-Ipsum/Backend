package com.loremipsum.lawconnectplatform.legalcase.domain.model.commands;

public record AddDocumentByLegalCaseIdCommand(
        String title,
        String description,
        Integer type,
        Integer status,
        Long legalCaseId
) {
}
