package com.loremipsum.lawconnectplatform.legalcase.domain.services;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetAllLegalCasesQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetLegalCaseByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LegalCaseQueryService {
    List<LegalCase> handle(GetAllLegalCasesQuery query);
    Optional<LegalCase> handle(GetLegalCaseByIdQuery query);
}
