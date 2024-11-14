package com.loremipsum.lawconnectplatform.consultation.domain.model.queries;

import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.PaymentC;

public record GetAllConsultationsByPaymentIdQuery(PaymentC paymentId) {
}
