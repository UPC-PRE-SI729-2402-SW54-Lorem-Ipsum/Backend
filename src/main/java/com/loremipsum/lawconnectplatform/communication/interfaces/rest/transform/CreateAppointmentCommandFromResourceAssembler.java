package com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateAppointmentCommand;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource){
        return new CreateAppointmentCommand(
                resource.consultationId(),
                resource.description(),
                resource.location()
        );
    }
}
