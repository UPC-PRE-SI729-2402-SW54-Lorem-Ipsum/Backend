package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

public record CreateVideoCallResource(
        Long consultationId,
        String description,
        String location
) {
}
