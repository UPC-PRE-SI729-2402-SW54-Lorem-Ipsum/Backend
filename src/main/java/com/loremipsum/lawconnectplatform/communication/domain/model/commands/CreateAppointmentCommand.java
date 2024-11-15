package com.loremipsum.lawconnectplatform.communication.domain.model.commands;

public record CreateAppointmentCommand(
        Long consultationId,
        String description,
        String location
) {
}
