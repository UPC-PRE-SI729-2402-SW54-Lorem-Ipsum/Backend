package com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.followup.interfaces.acl.FollowUpContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalFollowUpConsultationService {

    private final FollowUpContextFacade followUpContextFacade;

    public ExternalFollowUpConsultationService(FollowUpContextFacade followUpContextFacade) {
        this.followUpContextFacade = followUpContextFacade;
    }

    public void createNotification(
            String title,
            String description,
            Long clientId,
            Long consultationId
    ){
        followUpContextFacade.createNotification(
                title,
                description,
                clientId,
                consultationId
        );
    }
}
