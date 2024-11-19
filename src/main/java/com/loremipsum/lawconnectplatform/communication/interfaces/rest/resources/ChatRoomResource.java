package com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.communication.domain.model.entities.MessageItem;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

import java.util.List;

public record ChatRoomResource(
        Long id,
        ConsultationResource consultation,
        String status,
        List<MessageResource> messages
) {
}
