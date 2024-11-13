package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;

import java.util.Set;

public record LawyerResource(
        Long id,
        Profile profile,
        Set<String> lawyerTypes,
        Set<String> Prices
) {
}
