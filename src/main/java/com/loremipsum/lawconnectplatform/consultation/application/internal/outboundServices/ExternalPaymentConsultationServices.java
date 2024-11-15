package com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.feeing.interfaces.acl.PaymentContextFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalPaymentConsultationServices {

    private final PaymentContextFacade paymentContextFacade;

    public ExternalPaymentConsultationServices(PaymentContextFacade paymentContextFacade) {
        this.paymentContextFacade = paymentContextFacade;
    }

    public Optional<Payment> createPayment(Long clientId, Double amount, Integer status) {
        return paymentContextFacade.createPayment(clientId, amount, status);
    }
}
