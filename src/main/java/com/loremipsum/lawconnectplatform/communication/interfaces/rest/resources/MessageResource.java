package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;

public record MessageResource (
        Long id,
        String content,
        Long chatRoomId,
        boolean isRead,
        String senderType
) {
}
