package com.loremipsum.lawconnectplatform.legalcase.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CloseLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseCommandService;
import com.loremipsum.lawconnectplatform.legalcase.infrastructure.persistence.jpa.repositories.LegalCaseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LegalCaseCommandServiceImpl implements LegalCaseCommandService {

    private final LegalCaseRepository legalCaseRepository;

    public LegalCaseCommandServiceImpl(LegalCaseRepository legalCaseRepository) {
        this.legalCaseRepository = legalCaseRepository;
    }

    @Override
    public Optional<LegalCase> handle(CreateLegalCaseCommand command) {

        var legalCase = new LegalCase(command);
        legalCaseRepository.save(legalCase);

        return Optional.of(legalCase);
    }

    @Override
    public void handle(CloseLegalCaseCommand command) {
            var legalCase = legalCaseRepository.findById(command.legalCaseId());
            legalCase.ifPresent(LegalCase::close);
            legalCaseRepository.save(legalCase.get());
    }
}
