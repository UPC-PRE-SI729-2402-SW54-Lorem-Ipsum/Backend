package com.loremipsum.lawconnectplatform.iam.interfaces.rest.resources;

import java.util.List;

public record SignUpResource(
        String email,
        String password,
        List<String> roles,
        String firstName,
        String lastName,
        String phoneNumber,
        String address,
        String dni,
        String image_url

) {
}
