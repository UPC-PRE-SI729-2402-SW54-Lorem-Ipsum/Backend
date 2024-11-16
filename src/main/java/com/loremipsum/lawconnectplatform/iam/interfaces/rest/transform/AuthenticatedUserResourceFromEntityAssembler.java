package com.loremipsum.lawconnectplatform.iam.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.iam.domain.model.aggregates.User;
import com.loremipsum.lawconnectplatform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(
                user.getId(),
                user.getUsername(),
                token,
                user.getRoles().toString()
        );
    }
}
