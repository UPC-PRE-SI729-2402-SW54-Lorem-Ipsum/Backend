package com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.interfaces.rest.resources.CreateLegalCaseResource;

public class CreateLegalCaseCommandFromResourceAssembler {
    public static CreateLegalCaseCommand toCommandFromResource(CreateLegalCaseResource resource) {
        return new CreateLegalCaseCommand(
                resource.title(),
                resource.description(),
                resource.consultationId()
        );
    }
}
