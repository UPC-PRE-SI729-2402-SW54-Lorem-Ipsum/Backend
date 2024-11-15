package com.loremipsum.lawconnectplatform.consultation.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateChatRoomEvent extends ApplicationEvent {

    private Long consultationId;

    public CreateChatRoomEvent(Object source, Long consultationId) {
        super(source);
        this.consultationId = consultationId;
    }
}
