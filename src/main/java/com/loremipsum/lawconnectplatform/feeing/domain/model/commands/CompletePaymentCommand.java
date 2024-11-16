package com.loremipsum.lawconnectplatform.feeing.domain.model.commands;

import java.time.LocalDate;

public record CompletePaymentCommand(
        Long paymentId,
        String cardNumber,
        LocalDate expirationDate,
        String cvv
) {
}
