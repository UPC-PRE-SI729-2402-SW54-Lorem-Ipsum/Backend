package com.loremipsum.lawconnectplatform.legalcase.domain.services;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.AddDocumentByLegalCaseIdCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.ChangeDocumentStatusCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.DeleteLegalCaseCommand;
import org.springframework.stereotype.Service;

@Service
public interface DocumentsCommandService {
    void handle(AddDocumentByLegalCaseIdCommand command);
    void handle(ChangeDocumentStatusCommand command);
}
