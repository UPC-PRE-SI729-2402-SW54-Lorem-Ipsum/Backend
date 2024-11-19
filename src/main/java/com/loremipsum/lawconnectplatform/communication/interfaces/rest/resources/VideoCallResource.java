package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

public record VideoCallResource(
        Long id,
        ConsultationResource consultation,
        String description,
        String status
) {
}
