package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources;

public record CreateConsultationResource(
        Long lawyerId,
        Long clientId,
        String description,
        Integer Currency,
        Integer type,
        String title
) {
}
