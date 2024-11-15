package com.loremipsum.lawconnectplatform.feeing.application.internal.eventhandlers;

import com.loremipsum.lawconnectplatform.feeing.application.internal.outboundServices.ExternalConsultationPaymentService;
import com.loremipsum.lawconnectplatform.feeing.domain.model.events.PaymentCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentCompletedEventHandler {

    private final ExternalConsultationPaymentService externalConsultationPaymentService;

    public PaymentCompletedEventHandler(ExternalConsultationPaymentService externalConsultationPaymentService) {
        this.externalConsultationPaymentService = externalConsultationPaymentService;
    }

    @EventListener(PaymentCompletedEvent.class)
    public void onPaymentCompleted(PaymentCompletedEvent event) {
        var consultationId = externalConsultationPaymentService.getConsultationByPaymentId(event.getPaymentId());
        externalConsultationPaymentService.changeConsultationStatus(consultationId.get().getPaymentId());
    }
}
