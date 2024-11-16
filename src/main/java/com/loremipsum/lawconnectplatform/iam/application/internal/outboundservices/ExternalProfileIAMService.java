package com.loremipsum.lawconnectplatform.iam.application.internal.outboundservices;

import com.loremipsum.lawconnectplatform.profiles.interfaces.acl.ProfileContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalProfileIAMService {

    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileIAMService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    public void createClient(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            String dni,
            String image_url
    ) {
        profileContextFacade.createClient(
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                dni,
                image_url
        );
    }

    public void createLawyer(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            String dni,
            String image_url
    ) {
        profileContextFacade.createLawyer(
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                dni,
                image_url
        );
    }
}

