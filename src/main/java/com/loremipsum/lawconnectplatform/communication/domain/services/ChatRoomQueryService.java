package com.loremipsum.lawconnectplatform.communication.domain.services;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.ChatRoom;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetChatRoomByConsultationIdQuery;

import java.util.Optional;

public interface ChatRoomQueryService {
    Optional<ChatRoom> handle(GetChatRoomByConsultationIdQuery query);
}
