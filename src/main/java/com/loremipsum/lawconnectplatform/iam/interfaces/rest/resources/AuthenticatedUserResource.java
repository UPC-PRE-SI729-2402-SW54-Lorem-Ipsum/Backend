package com.loremipsum.lawconnectplatform.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(
        Long id,
        String username,
        String token,
        String role
) {

}
