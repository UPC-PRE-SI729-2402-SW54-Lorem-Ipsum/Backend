package com.loremipsum.lawconnectplatform.communication.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalFollowUpCommunicationService;
import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalPaymentCommunicationService;
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
    private final ExternalFollowUpCommunicationService externalFollowUpCommunicationService;
    private final ExternalPaymentCommunicationService externalPaymentCommunicationService;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService, ExternalFollowUpCommunicationService externalFollowUpCommunicationService, ExternalPaymentCommunicationService externalPaymentCommunicationService) {
        this.appointmentRepository = appointmentRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
        this.externalFollowUpCommunicationService = externalFollowUpCommunicationService;
        this.externalPaymentCommunicationService = externalPaymentCommunicationService;
    }

    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {

        var consultation = externalConsultationCommunicationService.getConsultationById(command.consultationId());

        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        var payment = externalPaymentCommunicationService.getPaymentById(consultation.get().getPaymentId());

        var appointment = new Appointment(command, consultation.get());

        appointmentRepository.save(appointment);

        var message = command.description() + " - " + command.location();

        externalFollowUpCommunicationService.createNotification(
                "Appointment created",
                message,
                payment.get().getClientId(),
                consultation.get().getId()
        );

        return Optional.of(appointment);
    }
}
