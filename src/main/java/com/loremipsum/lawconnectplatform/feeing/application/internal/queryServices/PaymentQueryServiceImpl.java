package com.loremipsum.lawconnectplatform.feeing.application.internal.queryServices;

import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.feeing.application.internal.outboundServices.ExternalConsultationPaymentService;
import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.feeing.domain.model.queries.GetAllPaymentByClientIdQuery;
import com.loremipsum.lawconnectplatform.feeing.domain.model.queries.GetPaymentByIdQuery;
import com.loremipsum.lawconnectplatform.feeing.domain.services.PaymentQueryService;
import com.loremipsum.lawconnectplatform.feeing.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;
    private final ExternalConsultationPaymentService externalConsultationPaymentService;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository, ExternalConsultationPaymentService externalConsultationPaymentService) {
        this.paymentRepository = paymentRepository;
        this.externalConsultationPaymentService = externalConsultationPaymentService;
    }

    @Override
    public List<Payment> handle(GetAllPaymentsByConsultationIdQuery query) {
        var consultation = externalConsultationPaymentService.getConsultationById(query.consultationId());
        return paymentRepository.findAllByConsultation(consultation.get());
    }

    @Override
    public List<Payment> handle(GetAllPaymentByClientIdQuery query) {
        return paymentRepository.findAllByClientId(query.clientId());
    }

    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query) {
        return paymentRepository.findById(query.paymentId());
    }
}
