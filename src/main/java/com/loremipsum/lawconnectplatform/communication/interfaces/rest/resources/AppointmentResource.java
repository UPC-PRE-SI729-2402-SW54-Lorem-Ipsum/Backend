package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;

public record AppointmentResource(
        Long id,
        String description,
        Consultation consultation,
        String location,
        String status
) {
}
