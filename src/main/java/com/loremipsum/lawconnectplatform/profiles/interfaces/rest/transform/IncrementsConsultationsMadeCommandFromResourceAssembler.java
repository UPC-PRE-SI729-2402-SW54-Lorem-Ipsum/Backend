package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.IncrementConsultationsMadeCommand;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.IncrementConsultationsMadeResource;

public class IncrementsConsultationsMadeCommandFromResourceAssembler {
    public static IncrementConsultationsMadeCommand ToCommandFromResource(IncrementConsultationsMadeResource resource){
        return new IncrementConsultationsMadeCommand(
                resource.id()
        );
    }
}
