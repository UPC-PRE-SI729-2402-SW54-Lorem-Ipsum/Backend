package com.loremipsum.lawconnectplatform.feeing.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByClientId(Long clientId);
    List<Payment> findAllByConsultation(Consultation consultationId);
}
