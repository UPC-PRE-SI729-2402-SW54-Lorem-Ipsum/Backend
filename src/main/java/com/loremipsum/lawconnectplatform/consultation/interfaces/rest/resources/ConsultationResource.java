package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;

import java.util.List;

public record ConsultationResource(
        Long id,
        Long lawyerId,
        Long clientId,
        List<Payment> payment,
        String description,
        String consultationType,
        String applicationStatus
) {
}
