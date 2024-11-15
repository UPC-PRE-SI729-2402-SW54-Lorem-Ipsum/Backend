package com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.VideoCall;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.VideoCallResource;

public class VideoCallResourceFromEntityAssembler {
    public static VideoCallResource toResourceFromEntity(VideoCall entity){
        return new VideoCallResource(
                entity.getId(),
                entity.getConsultation(),
                entity.getDescription(),
                entity.getStatus().toString()
        );
    }
}
