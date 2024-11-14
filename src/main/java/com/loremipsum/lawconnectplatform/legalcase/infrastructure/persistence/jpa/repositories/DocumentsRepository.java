package com.loremipsum.lawconnectplatform.legalcase.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.entities.DocumentsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentsRepository extends JpaRepository<DocumentsItem, Long> {
    List<DocumentsItem> findAllByLegalCase(LegalCase legalCase);
}
