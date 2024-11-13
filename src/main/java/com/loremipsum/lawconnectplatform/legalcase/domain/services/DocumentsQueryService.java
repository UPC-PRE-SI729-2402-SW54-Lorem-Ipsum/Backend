package com.loremipsum.lawconnectplatform.legalcase.domain.services;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.entities.DocumentsItem;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetAllDocumentsByLegalCaseQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetDocumentByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface DocumentsQueryService {
    List<DocumentsItem> handle(GetAllDocumentsByLegalCaseQuery query);
    Optional<DocumentsItem> handle(GetDocumentByIdQuery query);
}
