package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;

public record ClientResource(
        Long id,
        Profile profile,
        Integer consultationCount,
        Integer paidServices
) {
}
