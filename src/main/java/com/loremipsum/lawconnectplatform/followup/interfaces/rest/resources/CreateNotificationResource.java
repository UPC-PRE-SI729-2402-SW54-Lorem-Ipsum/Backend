package com.loremipsum.lawconnectplatform.followup.interfaces.rest.resources;

public record CreateNotificationResource(
        String title,
        String description,
        Long clientId,
        Long legalCaseId
) {
}
