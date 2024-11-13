package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource ToResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getName().firstName(),
                entity.getName().lastName(),
                entity.getEmail().address(),
                entity.getPhoneNumber(),
                entity.getDNI(),
                entity.getImage_url(),
                entity.getAddress()
        );
    }
}
