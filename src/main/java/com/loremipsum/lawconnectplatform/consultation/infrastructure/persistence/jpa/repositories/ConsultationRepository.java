package com.loremipsum.lawconnectplatform.consultation.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.LawyerC;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.PaymentC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    boolean existsByPaymentIdAndLawyerId(PaymentC paymentId, LawyerC lawyerId);
    List<Consultation> findAllByLawyerId(LawyerC lawyerId);
    List<Consultation> findAllByPaymentId(PaymentC paymentId);
    List<Consultation> findAllByPaymentIdAndLawyerId(PaymentC paymentId, LawyerC lawyerId);
}
