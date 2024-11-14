package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.AddLawyerPricesCommand;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.AddLawyerPricesResource;

public class AddLawyerPricesCommandFromResourceAssembler {
    public static AddLawyerPricesCommand ToCommandFromResource(AddLawyerPricesResource resource) {
        return new AddLawyerPricesCommand(
                resource.lawyerId(),
                resource.chatPrice(),
                resource.videoCallPrice(),
                resource.faceToFacePrice()
        );
    }
}
