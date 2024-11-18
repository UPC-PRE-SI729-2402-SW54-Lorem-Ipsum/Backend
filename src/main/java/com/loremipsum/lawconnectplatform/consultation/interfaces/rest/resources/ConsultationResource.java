package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.feeing.interfaces.rest.resources.PaymentResource;

import java.util.List;

public record ConsultationResource(
        Long id,
        Long lawyerId,
        Long clientId,
        List<PaymentResource> payment,
        String description,
        String consultationType,
        String applicationStatus
) {
}
