package com.loremipsum.lawconnectplatform.communication.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.Appointment;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllAppointmentsByConsultationIdQuery;
import com.loremipsum.lawconnectplatform.communication.domain.services.AppointmentQueryService;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.appointmentRepository = appointmentRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByConsultationIdQuery query) {
        var consultation = externalConsultationCommunicationService.getConsultationById(query.consultationId());
        return appointmentRepository.findAllByConsultation(consultation.get());
    }
}
