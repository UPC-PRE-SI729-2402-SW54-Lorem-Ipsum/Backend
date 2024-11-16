package com.loremipsum.lawconnectplatform.profiles.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetAllClientsQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetClientByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetClientIdByEmailQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.EmailAddress;
import com.loremipsum.lawconnectplatform.profiles.domain.services.ClientQueryService;
import com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.ClientRepository;
import com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientRepository clientRepository;
    private final ProfileRepository profileRepository;

    public ClientQueryServiceImpl(ClientRepository clientRepository, ProfileRepository profileRepository) {
        this.clientRepository = clientRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Client> handle(GetAllClientsQuery query) {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> handle(GetClientByIdQuery query) {
        return clientRepository.findById(query.clientId());
    }

    @Override
    public Optional<Long> handle(GetClientIdByEmailQuery query) {
        var profile = profileRepository.findByEmail(new EmailAddress(query.email()));
        var client = clientRepository.findByProfile(profile.get());
        return Optional.of(client.get().getId());
    }
}
