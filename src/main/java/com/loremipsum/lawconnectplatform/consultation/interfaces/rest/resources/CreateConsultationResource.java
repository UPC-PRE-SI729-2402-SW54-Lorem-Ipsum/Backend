package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources;

public record CreateConsultationResource(
        Long lawyerId,
        Long paymentId,
        String consultationType,
        String description
) {
}
