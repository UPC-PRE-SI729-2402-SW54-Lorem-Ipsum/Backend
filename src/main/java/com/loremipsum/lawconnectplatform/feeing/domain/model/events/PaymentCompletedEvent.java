package com.loremipsum.lawconnectplatform.feeing.domain.model.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public final class PaymentCompletedEvent extends ApplicationEvent {
    private Long paymentId;
    public PaymentCompletedEvent(Object source, Long paymentId) {
        super(source);
        this.paymentId = paymentId;
    }
}
