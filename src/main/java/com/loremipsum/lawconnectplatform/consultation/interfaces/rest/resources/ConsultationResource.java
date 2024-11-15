package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;

public record ConsultationResource(
        Long id,
        Long lawyerId,
        Payment payment,
        String consultationStatus,
        String description,
        String consultationType
) {
}
