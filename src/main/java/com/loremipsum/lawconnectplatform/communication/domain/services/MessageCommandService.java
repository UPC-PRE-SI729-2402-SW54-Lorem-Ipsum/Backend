package com.loremipsum.lawconnectplatform.communication.domain.services;

import com.loremipsum.lawconnectplatform.communication.domain.model.commands.AddMessageByChatRoomIdCommand;


public interface MessageCommandService {
    void handle(AddMessageByChatRoomIdCommand command);
}
