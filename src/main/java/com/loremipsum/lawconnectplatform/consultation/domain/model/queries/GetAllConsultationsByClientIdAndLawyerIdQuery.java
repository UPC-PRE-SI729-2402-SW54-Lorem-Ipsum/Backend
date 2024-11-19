package com.loremipsum.lawconnectplatform.consultation.domain.model.queries;

public record GetAllConsultationsByClientIdAndLawyerIdQuery(
        Long clientId,
        Long lawyerId
) {
}
