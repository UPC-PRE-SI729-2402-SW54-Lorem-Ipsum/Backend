package com.loremipsum.lawconnectplatform.communication.domain.model.commands;

public record AddMessageByChatRoomIdCommand(
        Long chatRoomId,
        String message,
        Integer sender
) {
}
