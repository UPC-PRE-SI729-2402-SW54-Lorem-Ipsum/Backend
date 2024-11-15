package com.loremipsum.lawconnectplatform.communication.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.Appointment;
import com.loremipsum.lawconnectplatform.communication.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity){
        return new AppointmentResource(
                entity.getId(),
                entity.getDescription(),
                entity.getConsultation(),
                entity.getLocation(),
                entity.getStatus().toString()
        );
    }
}
