package com.loremipsum.lawconnectplatform.feeing.domain.services;

import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.CompletePaymentCommand;
import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.CreatePaymentCommand;
import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.DeletePaymentCommand;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payment> handle(CreatePaymentCommand command);
    Optional<Payment> handle(CompletePaymentCommand command);
    void handle(DeletePaymentCommand command);
}
