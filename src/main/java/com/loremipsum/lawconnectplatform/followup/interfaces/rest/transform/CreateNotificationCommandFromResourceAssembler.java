package com.loremipsum.lawconnectplatform.followup.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.followup.domain.model.commands.CreateNotificationCommand;
import com.loremipsum.lawconnectplatform.followup.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
                resource.title(),
                resource.description(),
                resource.clientId(),
                resource.legalCaseId()
        );
    }
}
