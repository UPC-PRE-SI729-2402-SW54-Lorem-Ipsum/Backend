package com.loremipsum.lawconnectplatform.communication.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.Appointment;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateAppointmentCommand;
import com.loremipsum.lawconnectplatform.communication.domain.services.AppointmentCommandService;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.appointmentRepository = appointmentRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {

        var consultation = externalConsultationCommunicationService.getConsultationById(command.consultationId());

        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        var appointment = new Appointment(command, consultation.get());

        appointmentRepository.save(appointment);

        return Optional.of(appointment);
    }
}
