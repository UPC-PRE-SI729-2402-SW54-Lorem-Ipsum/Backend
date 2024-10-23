package com.loremipsum.lawconnectplatform.consultation.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    boolean existsByClientIdAndLawyerId(Long clientId, Long lawyerId);
    List<Consultation> findAllByLawyerId(Long lawyerId);
}
