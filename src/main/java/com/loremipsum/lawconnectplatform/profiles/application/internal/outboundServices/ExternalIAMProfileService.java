package com.loremipsum.lawconnectplatform.profiles.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.iam.interfaces.acl.IamContextFacade;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ExternalIAMProfileService {

    private final IamContextFacade iamContextFacade;

    public ExternalIAMProfileService(
            @Lazy IamContextFacade iamContextFacade
    ) {
        this.iamContextFacade = iamContextFacade;
    }

    public Long getUserIdByUsername(String username) {
        return iamContextFacade.fetchUserIdByUsername(username);
    }

}
