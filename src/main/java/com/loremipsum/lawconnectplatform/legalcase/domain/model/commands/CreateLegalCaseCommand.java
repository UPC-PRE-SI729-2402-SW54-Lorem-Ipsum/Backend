package com.loremipsum.lawconnectplatform.legalcase.domain.model.commands;

public record CreateLegalCaseCommand(
        String title,
        String description,
        Long consultationId
) {
}
