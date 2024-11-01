package com.loremipsum.lawconnectplatform.consultation.domain.model.queries;

import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.LawyerC;

public record GetAllConsultationsByLawyerIdQuery(LawyerC lawyerId) {
}
