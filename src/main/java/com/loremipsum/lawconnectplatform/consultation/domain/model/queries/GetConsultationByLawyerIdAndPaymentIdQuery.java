package com.loremipsum.lawconnectplatform.consultation.domain.model.queries;

import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.LawyerC;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.PaymentC;

public record GetConsultationByLawyerIdAndPaymentIdQuery(LawyerC lawyerId, PaymentC paymentId) {
}
