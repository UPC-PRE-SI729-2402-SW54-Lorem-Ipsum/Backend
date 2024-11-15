package com.loremipsum.lawconnectplatform.consultation.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices.ExternalPaymentConsultationServices;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.*;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.PaymentC;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationQueryService;
import com.loremipsum.lawconnectplatform.consultation.infrastructure.persistence.jpa.repositories.ConsultationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationQueryServiceImpl implements ConsultationQueryService {
    private final ConsultationRepository consultationRepository;
    private final ExternalPaymentConsultationServices externalPaymentConsultationServices;

    public ConsultationQueryServiceImpl(ConsultationRepository consultationRepository, ExternalPaymentConsultationServices externalPaymentConsultationServices) {
        this.consultationRepository = consultationRepository;
        this.externalPaymentConsultationServices = externalPaymentConsultationServices;
    }


    @Override
    public Optional<Consultation> handle(GetConsultationByIdQuery query) {
        return consultationRepository.findById(query.consultationId());
    }

    @Override
    public Optional<Consultation> handle(GetConsultationByLawyerIdAndPaymentIdQuery query) {
        var paymentId = externalPaymentConsultationServices.getPaymentById(query.paymentId());
        return consultationRepository.findByPaymentAndLawyerId(paymentId.get(), query.lawyerId());
    }

    @Override
    public Optional<Consultation> handle(GetConsultationByPaymentIdQuery query) {
        var paymentId = externalPaymentConsultationServices.getPaymentById(query.paymentId());
        return consultationRepository.findByPayment(paymentId.get());
    }

    @Override
    public Optional<Long> handle(GetPaymentIdByConsultationIdQuery query) {
        var paymentId = consultationRepository.findById(query.consultationId()).get().getPaymentId().longValue();
        return Optional.of(paymentId);
    }

    @Override
    public List<Consultation> handle(GetAllConsultationsByLawyerIdQuery query) {
        return consultationRepository.findAllByLawyerId(query.lawyerId());
    }

    @Override
    public List<Consultation> handle(GetAllConsultationsByPaymentIdQuery query) {
        var paymentId = externalPaymentConsultationServices.getPaymentById(query.paymentId());
        return consultationRepository.findAllByPayment(paymentId.get());
    }
}
