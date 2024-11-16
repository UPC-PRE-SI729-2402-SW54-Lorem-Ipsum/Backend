package com.loremipsum.lawconnectplatform.iam.domain.model.commands;

import com.loremipsum.lawconnectplatform.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(
        String email,
        String password,
        List<Role> roles,
        String firstName,
        String lastName,
        String phoneNumber,
        String address,
        String dni,
        String image_url
) {
}