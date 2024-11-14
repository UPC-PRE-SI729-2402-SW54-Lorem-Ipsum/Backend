package com.loremipsum.lawconnectplatform.profiles.domain.model.commands;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;

public record CreateClientCommand(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        String dni,
        String image_url
) {
}
