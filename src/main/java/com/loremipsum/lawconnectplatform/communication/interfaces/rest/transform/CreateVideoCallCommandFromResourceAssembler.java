package com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateVideoCallCommand;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.CreateVideoCallResource;

public class CreateVideoCallCommandFromResourceAssembler {
    public static CreateVideoCallCommand toCommandFromResource(CreateVideoCallResource resource){
        return new CreateVideoCallCommand(
                resource.consultationId(),
                resource.description()
        );
    }
}
