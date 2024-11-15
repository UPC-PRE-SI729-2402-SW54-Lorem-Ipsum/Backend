package com.loremipsum.lawconnectplatform.communication.domain.services;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateChatRoomCommand;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.DeleteChatRoomCommand;

import java.util.Optional;

public interface ChatRoomCommandService {
    Optional<ChatRoom> handle(CreateChatRoomCommand command);
    void handle(DeleteChatRoomCommand command);
}
