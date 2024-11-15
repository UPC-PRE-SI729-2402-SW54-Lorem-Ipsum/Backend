package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;

public record VideoCallResource(
        Long id,
        Consultation consultation,
        String description,
        String status
) {
}
