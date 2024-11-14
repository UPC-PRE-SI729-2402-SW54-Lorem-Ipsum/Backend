package com.loremipsum.lawconnectplatform.profiles.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetProfileByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.services.ProfileQueryService;
import com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }
}
