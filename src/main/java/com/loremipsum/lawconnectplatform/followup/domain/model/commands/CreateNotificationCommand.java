package com.loremipsum.lawconnectplatform.followup.domain.model.commands;

public record CreateNotificationCommand(
        String title,
        String description,
        Long clientId,
        Long legalCaseId
) {
}
