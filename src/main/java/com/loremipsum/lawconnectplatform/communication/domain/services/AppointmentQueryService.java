package com.loremipsum.lawconnectplatform.communication.domain.services;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.Appointment;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllAppointmentsByConsultationIdQuery;

import java.util.List;

public interface AppointmentQueryService {
    List<Appointment> handle(GetAllAppointmentsByConsultationIdQuery query);
}
