package com.loremipsum.lawconnectplatform.feeing.domain.model.commands;

public record CreatePaymentCommand(
        Long consultationId,
        Long clientId,
        Double amount,
        Integer currency
) {
}
