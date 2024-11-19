package com.loremipsum.lawconnectplatform.legalcase.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {
    Optional<LegalCase> findByConsultation(Consultation consultation);
}
