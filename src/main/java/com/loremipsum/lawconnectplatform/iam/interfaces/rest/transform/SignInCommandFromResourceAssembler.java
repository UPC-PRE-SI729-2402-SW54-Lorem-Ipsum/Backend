package com.loremipsum.lawconnectplatform.iam.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.iam.domain.model.commands.SignInCommand;
import com.loremipsum.lawconnectplatform.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
