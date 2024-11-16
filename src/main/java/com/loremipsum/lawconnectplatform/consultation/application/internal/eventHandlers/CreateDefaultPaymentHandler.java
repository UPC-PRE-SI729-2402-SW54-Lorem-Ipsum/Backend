package com.loremipsum.lawconnectplatform.consultation.application.internal.eventHandlers;

import com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices.ExternalPaymentConsultationServices;
import com.loremipsum.lawconnectplatform.consultation.domain.model.events.CreateDefaultPaymentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class CreateDefaultPaymentHandler {

    private final ExternalPaymentConsultationServices externalPaymentConsultationServices;

    public CreateDefaultPaymentHandler(ExternalPaymentConsultationServices externalPaymentConsultationServices) {
        this.externalPaymentConsultationServices = externalPaymentConsultationServices;
    }

    @EventListener(CreateDefaultPaymentEvent.class)
    public void on(CreateDefaultPaymentEvent event) {
        externalPaymentConsultationServices.createPayment(event.getConsultationId() ,event.getClientId(), event.getAmount(), event.getCurrency());
    }
}
