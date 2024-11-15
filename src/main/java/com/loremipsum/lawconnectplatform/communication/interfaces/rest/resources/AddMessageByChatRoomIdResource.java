package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

public record AddMessageByChatRoomIdResource(
        Long chatRoomId,
        String message,
        Integer sender
) {
}
