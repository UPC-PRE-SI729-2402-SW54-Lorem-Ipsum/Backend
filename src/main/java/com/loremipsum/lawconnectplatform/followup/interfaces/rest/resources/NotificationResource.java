package com.loremipsum.lawconnectplatform.followup.interfaces.rest.resources;

public record NotificationResource(
        Long id,
        String title,
        String description,
        Long clientId,
        Long legalCaseId
) {
}
