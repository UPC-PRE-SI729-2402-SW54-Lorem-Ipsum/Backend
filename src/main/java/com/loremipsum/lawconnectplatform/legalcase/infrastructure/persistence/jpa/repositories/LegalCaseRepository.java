package com.loremipsum.lawconnectplatform.legalcase.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {

}
