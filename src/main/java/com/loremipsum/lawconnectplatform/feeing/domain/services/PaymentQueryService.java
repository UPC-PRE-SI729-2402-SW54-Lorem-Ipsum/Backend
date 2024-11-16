package com.loremipsum.lawconnectplatform.feeing.domain.services;

import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.feeing.domain.model.queries.GetAllPaymentByClientIdQuery;
import com.loremipsum.lawconnectplatform.feeing.domain.model.queries.GetPaymentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    List<Payment> handle(GetAllPaymentsByConsultationIdQuery query);
    List<Payment> handle(GetAllPaymentByClientIdQuery query);
    Optional<Payment> handle(GetPaymentByIdQuery query);
}
