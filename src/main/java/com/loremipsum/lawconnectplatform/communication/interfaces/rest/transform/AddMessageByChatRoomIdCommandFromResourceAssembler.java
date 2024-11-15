package com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.communication.domain.model.commands.AddMessageByChatRoomIdCommand;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.AddMessageByChatRoomIdResource;

public class AddMessageByChatRoomIdCommandFromResourceAssembler {
    public static AddMessageByChatRoomIdCommand toCommandFromResource(AddMessageByChatRoomIdResource resource){
        return new AddMessageByChatRoomIdCommand(
                resource.chatRoomId(),
                resource.message(),
                resource.sender()
        );
    }
}
