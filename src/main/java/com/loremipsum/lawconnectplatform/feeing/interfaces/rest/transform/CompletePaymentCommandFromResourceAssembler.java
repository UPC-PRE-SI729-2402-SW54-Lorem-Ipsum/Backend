package com.loremipsum.lawconnectplatform.feeing.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.CompletePaymentCommand;
import com.loremipsum.lawconnectplatform.feeing.interfaces.rest.resources.CompletePaymentResource;

import java.time.LocalDate;

public class CompletePaymentCommandFromResourceAssembler {
    public static CompletePaymentCommand toCommandFromResource(CompletePaymentResource resource, Long consultationId){

        String[] dateParts = resource.expirationDate().split("-");
        int year = Integer.parseInt(dateParts[0]);
        System.out.println(year);
        int month = Integer.parseInt(dateParts[1]);
        System.out.println(month);

        LocalDate expirationDateYYYYMM = LocalDate.of(year, month, 1);

        System.out.println(expirationDateYYYYMM);

        return new CompletePaymentCommand(
                consultationId,
                resource.cardNumber(),
                expirationDateYYYYMM,
                resource.cvv()
        );
    }
}
