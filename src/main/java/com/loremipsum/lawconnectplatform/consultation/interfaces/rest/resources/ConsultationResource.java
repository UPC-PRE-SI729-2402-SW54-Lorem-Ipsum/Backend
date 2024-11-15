package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources;

public record ConsultationResource(
        Long id,
        Long lawyerId,
        Long paymentId,
        String consultationStatus,
        String description
) {
}
