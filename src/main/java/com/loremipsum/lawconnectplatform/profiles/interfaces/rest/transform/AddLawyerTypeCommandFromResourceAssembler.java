package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.AddLawyerTypeCommand;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.AddLawyerTypeResource;

public class AddLawyerTypeCommandFromResourceAssembler {
    public static AddLawyerTypeCommand ToCommandFromResource(AddLawyerTypeResource resource) {
        return new AddLawyerTypeCommand(
                resource.lawyerId(),
                resource.lawyerTypeId()
        );
    }
}
