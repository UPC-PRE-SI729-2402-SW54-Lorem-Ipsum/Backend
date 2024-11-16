package com.loremipsum.lawconnectplatform.profiles.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetAllLawyersQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetLawyerByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetLawyerIdByEmailQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetLawyerTypeByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.EmailAddress;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.LawyerType;
import com.loremipsum.lawconnectplatform.profiles.domain.services.LawyerQueryService;
import com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.LawyerRepository;
import com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LawyerQueryServiceImpl implements LawyerQueryService {

    private final LawyerRepository lawyerRepository;
    private final ProfileRepository profileRepository;

    public LawyerQueryServiceImpl(LawyerRepository lawyerRepository, ProfileRepository profileRepository) {
        this.lawyerRepository = lawyerRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Lawyer> handle(GetAllLawyersQuery query) {
        return lawyerRepository.findAll();
    }

    @Override
    public Optional<Lawyer> handle(GetLawyerByIdQuery query) {
        return lawyerRepository.findById(query.lawyerId());
    }

    @Override
    public Set<LawyerType> handle(GetLawyerTypeByIdQuery query) {
        Optional<Lawyer> lawyer = lawyerRepository.findById(query.lawyerId());
        if (lawyer.isPresent()) {
            return lawyer.get().getLawyerTypes();
        } else {
            throw new IllegalArgumentException("Lawyer not found with ID: " + query.lawyerId());
        }
    }

    @Override
    public Optional<Long> handle(GetLawyerIdByEmailQuery query) {
        var profile = profileRepository.findByEmail(new EmailAddress(query.email()));
        var lawyer = lawyerRepository.findByProfile(profile.get());
        return Optional.of(lawyer.get().getId());
    }
}
