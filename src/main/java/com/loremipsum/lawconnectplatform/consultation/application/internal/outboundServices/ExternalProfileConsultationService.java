package com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.loremipsum.lawconnectplatform.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalProfileConsultationService {

    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileConsultationService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public Optional<Lawyer> getLawyerById(Long lawyerId){
        return profileContextFacade.getLawyerById(lawyerId);
    }

    public Optional<Client> getClientById(Long clientId){
        return profileContextFacade.getClientById(clientId);
    }
}
