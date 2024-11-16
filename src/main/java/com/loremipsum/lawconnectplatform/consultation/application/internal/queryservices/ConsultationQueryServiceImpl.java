package com.loremipsum.lawconnectplatform.consultation.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices.ExternalPaymentConsultationServices;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.queries.*;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationQueryService;
import com.loremipsum.lawconnectplatform.consultation.infrastructure.persistence.jpa.repositories.ConsultationRepository;
import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
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
        var payment = externalPaymentConsultationServices.getPaymentById(query.paymentId());
        return payment.flatMap(value -> consultationRepository.findByPayments(List.of(value))
                .filter(consultation -> {
                    consultation.getLawyerId();
                    return false;
                }));
    }

    @Override
    public Optional<Consultation> handle(GetConsultationByPaymentIdQuery query) {
        var payment = externalPaymentConsultationServices.getPaymentById(query.paymentId());
        if (payment.isPresent()) {
            return consultationRepository.findByPayments(List.of(payment.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<Payment>> handle(GetAllPaymentsByConsultationIdQuery query) {
        var consultation = consultationRepository.findById(query.consultationId());
        return consultation.map(Consultation::getPayments);
    }

    @Override
    public List<Consultation> handle(GetAllConsultationsByLawyerIdQuery query) {
        return consultationRepository.findAllByLawyerId(query.lawyerId());
    }
}
