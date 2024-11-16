package com.loremipsum.lawconnectplatform.consultation.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateDefaultPaymentEvent extends ApplicationEvent {
    private final Long consultationId;
    private final Long clientId;
    private final Double amount;
    private final Integer currency;

    public CreateDefaultPaymentEvent(Object source, Long consultationId, Long clientId, Double amount, Integer currency) {
        super(source);
        this.consultationId = consultationId;
        this.clientId = clientId;
        this.amount = amount;
        this.currency = currency;
    }
}
