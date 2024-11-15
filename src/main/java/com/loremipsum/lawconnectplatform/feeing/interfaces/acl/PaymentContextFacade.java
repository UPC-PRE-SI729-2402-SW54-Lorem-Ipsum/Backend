package com.loremipsum.lawconnectplatform.feeing.interfaces.acl;

import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.CreatePaymentCommand;
import com.loremipsum.lawconnectplatform.feeing.domain.services.PaymentCommandService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentContextFacade {
    private final PaymentCommandService paymentCommandService;

    public PaymentContextFacade(PaymentCommandService paymentCommandService) {
        this.paymentCommandService = paymentCommandService;
    }

    public Optional<Payment> createPayment(
            Long clientId,
            Double amount,
            Integer currency
    ){
        return paymentCommandService.handle(new CreatePaymentCommand(clientId,amount,currency));
    }
}
