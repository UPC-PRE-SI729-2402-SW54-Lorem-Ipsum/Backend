package com.loremipsum.lawconnectplatform.profiles.domain.services;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetAllClientsQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetClientByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetClientIdByEmailQuery;

import java.util.List;
import java.util.Optional;

public interface ClientQueryService {
    List<Client> handle(GetAllClientsQuery query);
    Optional<Client> handle(GetClientByIdQuery query);
    Optional<Long> handle(GetClientIdByEmailQuery query);
}
