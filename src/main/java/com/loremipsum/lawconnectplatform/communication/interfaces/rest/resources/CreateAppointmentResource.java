package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

public record CreateAppointmentResource (
        Long consultationId,
        String description,
        String location
) {
}
