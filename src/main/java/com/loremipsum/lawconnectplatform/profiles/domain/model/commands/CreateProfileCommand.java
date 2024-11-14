package com.loremipsum.lawconnectplatform.profiles.domain.model.commands;

public record CreateProfileCommand(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        String dni,
        String image_url
) {
}
