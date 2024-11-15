package com.loremipsum.lawconnectplatform.legalcase.domain.services;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CloseLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.DeleteLegalCaseCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface LegalCaseCommandService {
    Optional<LegalCase> handle(CreateLegalCaseCommand command);
    void handle(CloseLegalCaseCommand command);
    void handle(DeleteLegalCaseCommand command);
}
