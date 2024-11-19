package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

public record AppointmentResource(
        Long id,
        String description,
        ConsultationResource consultation,
        String location,
        String status
) {
}
