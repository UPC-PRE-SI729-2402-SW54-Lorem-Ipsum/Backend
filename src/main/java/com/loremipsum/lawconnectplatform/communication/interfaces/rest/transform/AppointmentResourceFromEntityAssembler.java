package com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.Appointment;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.AppointmentResource;
import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity, ConsultationResource consultationResource) {
        return new AppointmentResource(
                entity.getId(),
                entity.getDescription(),
                consultationResource,
                entity.getLocation(),
                entity.getStatus().toString()
        );
    }
}
