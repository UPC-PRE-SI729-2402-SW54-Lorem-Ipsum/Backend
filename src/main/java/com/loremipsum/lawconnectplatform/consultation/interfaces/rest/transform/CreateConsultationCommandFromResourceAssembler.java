package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.CreateConsultationResource;

public class CreateConsultationCommandFromResourceAssembler {
    public static CreateConsultationCommand toCommandFromResource(CreateConsultationResource resource){
        return new CreateConsultationCommand(
                resource.lawyerId(),
                resource.clientId(),
                resource.description(),
                resource.Currency()
        );
    }
}
