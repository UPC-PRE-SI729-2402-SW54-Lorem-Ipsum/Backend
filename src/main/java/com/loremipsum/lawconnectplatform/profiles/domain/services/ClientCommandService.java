package com.loremipsum.lawconnectplatform.profiles.domain.services;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateClientCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.IncrementConsultationsMadeCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.IncrementPaidServicesCommand;

import java.util.Optional;

public interface ClientCommandService {
    Optional<Client> handle(CreateClientCommand command);
    void handle(IncrementPaidServicesCommand command);
    void handle(IncrementConsultationsMadeCommand command);
}
