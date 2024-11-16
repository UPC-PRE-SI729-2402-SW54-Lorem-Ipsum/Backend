package com.loremipsum.lawconnectplatform.feeing.interfaces.acl;

import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.CreatePaymentCommand;
import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.DeletePaymentCommand;
import com.loremipsum.lawconnectplatform.feeing.domain.model.queries.GetPaymentByIdQuery;
import com.loremipsum.lawconnectplatform.feeing.domain.services.PaymentCommandService;
import com.loremipsum.lawconnectplatform.feeing.domain.services.PaymentQueryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentContextFacade {
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentContextFacade(
            @Lazy PaymentCommandService paymentCommandService,
            @Lazy PaymentQueryService paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    public Optional<Payment> createPayment(
            Long consultationId,
            Long clientId,
            Double amount,
            Integer currency
    ){
        return paymentCommandService.handle(new CreatePaymentCommand(consultationId, clientId,amount,currency));
    }

    public Optional<Payment> getPaymentById(Long paymentId){
        return paymentQueryService.handle(new GetPaymentByIdQuery(paymentId));
    }

    public void deletePaymentById(Long paymentId){
        paymentCommandService.handle(new DeletePaymentCommand(paymentId));
    }
}
