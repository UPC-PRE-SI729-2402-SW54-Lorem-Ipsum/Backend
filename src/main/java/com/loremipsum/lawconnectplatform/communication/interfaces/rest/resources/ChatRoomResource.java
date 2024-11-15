package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.communication.domain.model.entities.MessageItem;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;

import java.util.List;

public record ChatRoomResource(
        Long id,
        Consultation consultation,
        String status,
        List<MessageResource> messages
) {
}
