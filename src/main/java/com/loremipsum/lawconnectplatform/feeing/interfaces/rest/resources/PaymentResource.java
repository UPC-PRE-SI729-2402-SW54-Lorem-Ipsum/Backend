package com.loremipsum.lawconnectplatform.feeing.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.feeing.domain.model.valueObjects.PaymentStatus;

public record PaymentResource(
        Long id,
        Long clientId,
        String amount,
        PaymentStatus status
){

}
