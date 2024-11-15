package com.loremipsum.lawconnectplatform.consultation.application.internal.queryservices;

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

    public ConsultationQueryServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }


    @Override
    public Optional<Consultation> handle(GetConsultationByIdQuery query) {
        return consultationRepository.findById(query.consultationId());
    }

    @Override
    public Optional<Consultation> handle(GetConsultationByLawyerIdAndPaymentIdQuery query) {
        return consultationRepository.findByPaymentIdAndLawyerId(query.paymentId(), query.lawyerId());
    }

    @Override
    public Optional<Consultation> handle(GetConsultationByPaymentIdQuery query) {
        return consultationRepository.findByPaymentId(new PaymentC(query.PaymentId()));
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
        return consultationRepository.findAllByPaymentId(query.paymentId());
    }
}
