package com.loremipsum.lawconnectplatform.profiles.domain.services;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
