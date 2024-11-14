package com.loremipsum.lawconnectplatform.profiles.domain.services;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> handle(GetProfileByIdQuery query);
}
