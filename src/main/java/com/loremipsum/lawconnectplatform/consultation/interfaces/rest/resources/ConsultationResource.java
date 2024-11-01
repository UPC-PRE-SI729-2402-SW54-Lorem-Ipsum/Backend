package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources;

public record ConsultationResource(
        Long id,
        Long lawyerId,
        Long paymentId,
        String consultationType,
        String description
) {
}
