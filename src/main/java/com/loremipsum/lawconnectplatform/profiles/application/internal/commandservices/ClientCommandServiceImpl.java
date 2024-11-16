package com.loremipsum.lawconnectplatform.profiles.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.profiles.application.internal.outboundServices.ExternalIAMProfileService;
import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Profile;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateClientCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.IncrementConsultationsMadeCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.IncrementPaidServicesCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.EmailAddress;
import com.loremipsum.lawconnectplatform.profiles.domain.services.ClientCommandService;
import com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.ClientRepository;
import com.loremipsum.lawconnectplatform.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {

    private final ProfileRepository profileRepository;
    private final ClientRepository clientRepository;
    private final ExternalIAMProfileService externalIAMProfileService;

    public ClientCommandServiceImpl(ProfileRepository profileRepository, ClientRepository clientRepository, ExternalIAMProfileService externalIAMProfileService) {
        this.profileRepository = profileRepository;
        this.clientRepository = clientRepository;
        this.externalIAMProfileService = externalIAMProfileService;
    }


    @Override
    public Optional<Client> handle(CreateClientCommand command) {

        var profileId = profileRepository.findByEmail(new EmailAddress(command.email()));
        var profile = new Profile();

        if (profileId.isEmpty()) {
            var userId = externalIAMProfileService.getUserIdByUsername(command.email());
            profile = new Profile(command, userId);
        } else {
            throw new IllegalArgumentException("Client already exists");
        }

        var client = new Client(profile);

        profileRepository.save(profile);
        clientRepository.save(client);

        return Optional.of(client);
    }

    @Override
    public void handle(IncrementPaidServicesCommand command) {
        if(clientRepository.findById(command.clientId()).isPresent()){
            var client = clientRepository.findById(command.clientId()).get();
            client.incrementPaidServices();
            clientRepository.save(client);
        }
    }

    @Override
    public void handle(IncrementConsultationsMadeCommand command) {
        if(clientRepository.findById(command.clientId()).isPresent()){
            var client = clientRepository.findById(command.clientId()).get();
            client.incrementConsultationCount();
            clientRepository.save(client);
        }
    }
}
