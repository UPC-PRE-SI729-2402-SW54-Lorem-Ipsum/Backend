package com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.VideoCall;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.VideoCallResource;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

public class VideoCallResourceFromEntityAssembler {
    public static VideoCallResource toResourceFromEntity(VideoCall entity, ConsultationResource consultationResource){
        return new VideoCallResource(
                entity.getId(),
                consultationResource,
                entity.getDescription(),
                entity.getStatus().toString()
        );
    }
}
