package com.loremipsum.lawconnectplatform.feeing.interfaces.rest.resources;

public record CompletePaymentResource(
        String cardNumber,
        String expirationDate,
        String cvv
) {
}
