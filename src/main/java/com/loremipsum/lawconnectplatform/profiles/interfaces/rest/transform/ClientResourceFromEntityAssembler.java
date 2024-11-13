package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Client;
import com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources.ClientResource;

public class ClientResourceFromEntityAssembler {
    public static ClientResource ToResourceFromEntity(Client entity){
        return new ClientResource(
                entity.getId(),
                entity.getProfile(),
                entity.getConsultationCount(),
                entity.getPaidServices()
        );
    }
}
