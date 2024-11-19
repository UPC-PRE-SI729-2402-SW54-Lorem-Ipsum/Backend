package com.loremipsum.lawconnectplatform.legalcase.interfaces.acl;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.DeleteLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.queries.GetLegalCaseByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseCommandService;
import com.loremipsum.lawconnectplatform.legalcase.domain.services.LegalCaseQueryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Optional<LegalCase> getLegalCaseByConsultationId(Long consultationId){
        return legalCaseQueryService.handle(new GetLegalCaseByConsultationIdQuery(consultationId));
    }

    public void deleteLegalCase(Long legalCaseId){
        var consultation = legalCaseQueryService.handle(new GetLegalCaseByConsultationIdQuery(legalCaseId));
        legalCaseCommandService.handle(new DeleteLegalCaseCommand(consultation.get().getId()));
    }

}
