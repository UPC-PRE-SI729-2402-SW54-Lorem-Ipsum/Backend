package com.loremipsum.lawconnectplatform.consultation.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class CreateDefaultPaymentEvent extends ApplicationEvent {
    private final Long clientId;
    private final Double amount;
    private final Integer currency;

    public CreateDefaultPaymentEvent(Object source, Long clientId, Double amount, Integer currency) {
        super(source);
        this.clientId = clientId;
        this.amount = amount;
        this.currency = currency;
    }
}
