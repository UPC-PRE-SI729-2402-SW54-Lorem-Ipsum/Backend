package com.loremipsum.lawconnectplatform.feeing.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public record PaymentAmount(
        Double amount,
        @Enumerated(EnumType.STRING)
        Currency currency
) {
    public String paymentAmountToString(){
        return amount+" "+currency;
    }
}
