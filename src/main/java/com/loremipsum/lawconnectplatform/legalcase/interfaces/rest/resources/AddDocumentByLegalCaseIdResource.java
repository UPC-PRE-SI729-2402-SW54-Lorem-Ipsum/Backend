package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources;

public record AddDocumentByLegalCaseIdResource(
        String title,
        String description,
        Integer type,
        Integer status,
        Long legalCaseId
) {
}
