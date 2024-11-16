package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreatePaymentByConsultationIdCommand;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.AddPaymentResource;
import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.CreatePaymentCommand;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentByConsultationIdCommand toCommandFromResource(AddPaymentResource resource){
        return new CreatePaymentByConsultationIdCommand(
                resource.consultationId(),
                resource.amount(),
                resource.currency()
        );
    }
}
