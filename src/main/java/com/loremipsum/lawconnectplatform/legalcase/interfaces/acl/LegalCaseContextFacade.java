package com.loremipsum.lawconnectplatform.legalcase.interfaces.acl;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseCommandService;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseQueryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class LegalCaseContextFacade {

    private final LegalCaseCommandService legalCaseCommandService;
    private final LegalCaseQueryService legalCaseQueryService;

    public LegalCaseContextFacade(
            @Lazy LegalCaseCommandService legalCaseCommandService,
            @Lazy LegalCaseQueryService legalCaseQueryService
    ) {
        this.legalCaseCommandService = legalCaseCommandService;
        this.legalCaseQueryService = legalCaseQueryService;
    }

    public void createLegalCase(
            String title,
            String description,
            Long consultationId
    ){
        legalCaseCommandService.handle(new CreateLegalCaseCommand(
                        title,
                        description,
                        consultationId
                )
        );
    }

}
