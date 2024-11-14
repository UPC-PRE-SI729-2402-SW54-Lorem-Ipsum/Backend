package com.loremipsum.lawconnectplatform.consultation.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

public class ConsultationResourceFromEntityAssembler {
    public static ConsultationResource toResourceFromEntity(Consultation entity){
        return new ConsultationResource(
                entity.getId(),
                entity.getLawyerId(),
                entity.getPaymentId(),
                entity.getConsultationType(),
                entity.getDescription()
        );
    }
}
