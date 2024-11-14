package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.IncrementPaidServicesCommand;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.IncrementPaidServicesResource;

public class IncrementPaidServicesCommandFromResourceAssembler {
    public static IncrementPaidServicesCommand ToCommandFromResource(IncrementPaidServicesResource resource){
        return new IncrementPaidServicesCommand(resource.id());
    }
}
