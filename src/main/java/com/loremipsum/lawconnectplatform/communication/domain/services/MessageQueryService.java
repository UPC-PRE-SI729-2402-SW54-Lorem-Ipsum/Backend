package com.loremipsum.lawconnectplatform.communication.domain.services;

import com.loremipsum.lawconnectplatform.communication.domain.model.entities.MessageItem;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllMessagesByChatRoomIdQuery;

import java.util.List;
import java.util.Optional;

public interface MessageQueryService {
    List<MessageItem> handle(GetAllMessagesByChatRoomIdQuery query);
}
