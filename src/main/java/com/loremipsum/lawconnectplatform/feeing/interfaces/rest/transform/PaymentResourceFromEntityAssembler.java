package com.loremipsum.lawconnectplatform.feeing.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.feeing.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment entity){
        return new PaymentResource(
                entity.getId(),
                entity.getClientId(),
                entity.getAmount().paymentAmountToString(),
                entity.getStatus(),
                entity.getConsultation().getId()
        );
    }
}
