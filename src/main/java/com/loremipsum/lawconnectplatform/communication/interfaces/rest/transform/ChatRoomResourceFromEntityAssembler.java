package com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.ChatRoomResource;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

public class ChatRoomResourceFromEntityAssembler {
    public static ChatRoomResource toResourceFromEntity(ChatRoom entity, ConsultationResource consultationResource){
        return new ChatRoomResource(
                entity.getId(),
                consultationResource,
                entity.getStatus().toString(),
                entity.getMessages().getMessages().stream().map(MessageResourceFromEntityAssembler::toResourceFromEntity).toList()
        );
    }
}
