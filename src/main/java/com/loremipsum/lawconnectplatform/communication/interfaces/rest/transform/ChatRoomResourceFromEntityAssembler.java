package com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.ChatRoomResource;

public class ChatRoomResourceFromEntityAssembler {
    public static ChatRoomResource toResourceFromEntity(ChatRoom entity){
        return new ChatRoomResource(
                entity.getId(),
                entity.getConsultation(),
                entity.getStatus().toString(),
                entity.getMessages().getMessages().stream().map(MessageResourceFromEntityAssembler::toResourceFromEntity).toList()
        );
    }
}
