package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices.ExternalPaymentConsultationServices;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;
import com.loremipsum.lawconnectplatform.feeing.interfaces.rest.resources.PaymentResource;

import java.util.List;

public class ConsultationResourceFromEntityAssembler {
    public static ConsultationResource toResourceFromEntity(Consultation entity, List<PaymentResource> paymentResource) {
        return new ConsultationResource(
                entity.getId(),
                entity.getLawyerId(),
                entity.getClientId(),
                paymentResource,
                entity.getDescription(),
                entity.getConsultationType().toString(),
                entity.getApplicationStatus().toString()
        );
    }
}
