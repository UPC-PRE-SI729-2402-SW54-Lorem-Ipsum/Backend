package com.loremipsum.lawconnectplatform.consultation.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.LawyerC;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.PaymentC;
import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findAllByLawyerId(LawyerC lawyerId);
    List<Consultation> findAllByPayment(Payment payment);
    Optional<Consultation> findByPaymentAndLawyerId(Payment payment, LawyerC lawyerC);
    Optional<Consultation> findByPayment(Payment payment);
}
