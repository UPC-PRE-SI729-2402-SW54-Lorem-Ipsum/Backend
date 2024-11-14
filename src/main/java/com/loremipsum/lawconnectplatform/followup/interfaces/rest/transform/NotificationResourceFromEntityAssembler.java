package com.loremipsum.lawconnectplatform.followup.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.followup.domain.model.aggregates.Notification;
import com.loremipsum.lawconnectplatform.followup.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity){
        return new NotificationResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getClientId(),
                entity.getLegalCaseId()
        );
    }
}
