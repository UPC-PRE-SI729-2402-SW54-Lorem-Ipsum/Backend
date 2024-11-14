package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources;

public record DocumentsItemResource (
        Long id,
        String title,
        String type,
        String description,
        Long legalCaseId,
        String status
) {
}
