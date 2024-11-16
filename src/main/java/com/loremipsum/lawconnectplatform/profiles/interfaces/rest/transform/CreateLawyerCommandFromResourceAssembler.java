package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateLawyerCommand;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.CreateLawyerResource;

public class CreateLawyerCommandFromResourceAssembler {
    public static CreateLawyerCommand ToCommandFromResource(CreateLawyerResource resource)
    {
        return new CreateLawyerCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phoneNumber(),
                resource.address(),
                resource.dni(),
                resource.image_url()
        );
    }
}
