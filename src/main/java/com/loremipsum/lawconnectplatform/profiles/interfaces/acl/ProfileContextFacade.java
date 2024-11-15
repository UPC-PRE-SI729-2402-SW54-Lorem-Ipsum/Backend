package com.loremipsum.lawconnectplatform.profiles.interfaces.acl;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetClientByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.model.queries.GetLawyerByIdQuery;
import com.loremipsum.lawconnectplatform.profiles.domain.services.ClientCommandService;
import com.loremipsum.lawconnectplatform.profiles.domain.services.ClientQueryService;
import com.loremipsum.lawconnectplatform.profiles.domain.services.LawyerCommandService;
import com.loremipsum.lawconnectplatform.profiles.domain.services.LawyerQueryService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileContextFacade {

    private final LawyerQueryService lawyerQueryService;
    private final ClientQueryService clientQueryService;

    public ProfileContextFacade(LawyerQueryService lawyerQueryService, ClientQueryService clientQueryService) {
        this.lawyerQueryService = lawyerQueryService;
        this.clientQueryService = clientQueryService;
    }


    public Optional<Lawyer> getLawyerById(Long lawyerId){
        return lawyerQueryService.handle(new GetLawyerByIdQuery(lawyerId));
    }

    public Optional<Client> getClientById(Long clientId){
        return clientQueryService.handle(new GetClientByIdQuery(clientId));
    }
}
