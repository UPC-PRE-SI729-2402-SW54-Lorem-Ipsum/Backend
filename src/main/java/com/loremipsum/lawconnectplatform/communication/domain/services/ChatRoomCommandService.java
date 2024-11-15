package com.loremipsum.lawconnectplatform.communication.domain.services;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateChatRoomCommand;

import java.util.Optional;

public interface ChatRoomCommandService {
    Optional<ChatRoom> handle(CreateChatRoomCommand command);
}
