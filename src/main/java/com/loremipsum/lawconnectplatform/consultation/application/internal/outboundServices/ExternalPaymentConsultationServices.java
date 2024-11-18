package com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices;

import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.feeing.interfaces.acl.PaymentContextFacade;
import com.loremipsum.lawconnectplatform.feeing.interfaces.rest.resources.PaymentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalPaymentConsultationServices {

    private final PaymentContextFacade paymentContextFacade;

    public ExternalPaymentConsultationServices(PaymentContextFacade paymentContextFacade) {
        this.paymentContextFacade = paymentContextFacade;
    }

    public Optional<Payment> createPayment(Long consultationId, Long clientId, Double amount, Integer status) {
        return paymentContextFacade.createPayment(consultationId, clientId, amount, status);
    }

    public Optional<Payment> getPaymentById(Long paymentId) {
        return paymentContextFacade.getPaymentById(paymentId);
    }

    public void deletePayment(Long paymentId) {
        paymentContextFacade.deletePaymentById(paymentId);
    }

    public List<PaymentResource> createPaymentListResource(
            List<Payment> payment
    ) {
        return paymentContextFacade.createPaymentListResource(payment);
    }
}
