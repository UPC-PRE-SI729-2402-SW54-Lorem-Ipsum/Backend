package com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.legalcase.interfaces.acl.LegalCaseContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalLegalCaseConsultationService {

    private final LegalCaseContextFacade legalCaseContextFacade;

    public ExternalLegalCaseConsultationService(LegalCaseContextFacade legalCaseContextFacade) {
        this.legalCaseContextFacade = legalCaseContextFacade;
    }

    public void createLegalCase(
            String title,
            String description,
            Long consultationId
    ){
        legalCaseContextFacade.createLegalCase(
                title,
                description,
                consultationId
            );
    }
}
