package com.loremipsum.lawconnectplatform.profiles.interfaces.acl;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateClientCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateLawyerCommand;
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

    private final ClientCommandService clientCommandService;
    private final LawyerCommandService lawyerCommandService;
    private final LawyerQueryService lawyerQueryService;
    private final ClientQueryService clientQueryService;

    public ProfileContextFacade(ClientCommandService clientCommandService, LawyerCommandService lawyerCommandService, LawyerQueryService lawyerQueryService, ClientQueryService clientQueryService) {
        this.clientCommandService = clientCommandService;
        this.lawyerCommandService = lawyerCommandService;
        this.lawyerQueryService = lawyerQueryService;
        this.clientQueryService = clientQueryService;
    }


    public Optional<Lawyer> getLawyerById(Long lawyerId){
        return lawyerQueryService.handle(new GetLawyerByIdQuery(lawyerId));
    }

    public Optional<Client> getClientById(Long clientId){
        return clientQueryService.handle(new GetClientByIdQuery(clientId));
    }

    public void createClient(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            String dni,
            String image_url
    )
            {
        clientCommandService.handle(new CreateClientCommand(
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                dni,
                image_url
        ));
    }

    public void createLawyer(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            String dni,
            String image_url
    )
            {
        lawyerCommandService.handle(new CreateLawyerCommand(
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                dni,
                image_url
        ));
    }
}
