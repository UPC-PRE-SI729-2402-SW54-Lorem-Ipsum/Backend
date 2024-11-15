package com.loremipsum.lawconnectplatform.consultation.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices.*;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.*;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationCommandService;
import com.loremipsum.lawconnectplatform.consultation.infrastructure.persistence.jpa.repositories.ConsultationRepository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ConsultationCommandServiceImpl implements ConsultationCommandService {
    private final ConsultationRepository consultationRepository;
    private final ExternalPaymentConsultationServices externalPaymentConsultationServices;
    private final ExternalProfileConsultationService externalProfileConsultationService;
    private final ExternalCommunicationConsultationService externalCommunicationConsultationService;
    private final ExternalLegalCaseConsultationService externalLegalCaseConsultationService;
    private final ExternalFollowUpConsultationService externalFollowUpConsultationService;

    public ConsultationCommandServiceImpl(
            ConsultationRepository consultationRepository,
            @Lazy ExternalPaymentConsultationServices externalPaymentConsultationServices1,
            @Lazy ExternalProfileConsultationService externalProfileConsultationService1,
            @Lazy ExternalCommunicationConsultationService externalCommunicationConsultationService,
            @Lazy ExternalLegalCaseConsultationService externalLegalCaseConsultationService,
            @Lazy ExternalFollowUpConsultationService externalFollowUpConsultationService
    ) {
        this.consultationRepository = consultationRepository;
        this.externalPaymentConsultationServices = externalPaymentConsultationServices1;
        this.externalProfileConsultationService = externalProfileConsultationService1;
        this.externalCommunicationConsultationService = externalCommunicationConsultationService;
        this.externalLegalCaseConsultationService = externalLegalCaseConsultationService;
        this.externalFollowUpConsultationService = externalFollowUpConsultationService;
    }

    @Override
    public Long handle(CreateConsultationCommand command) {

        var lawyer = externalProfileConsultationService.getLawyerById(command.lawyerId());

        var payment = externalPaymentConsultationServices.createPayment(command.clientId(), lawyer.get().getPrices(), command.Currency());

        var consultation = new Consultation(command, payment.get());

        try {
            consultationRepository.save(consultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving consultation: " + e.getMessage());
        }

        externalCommunicationConsultationService.createChatRoom(consultation.getId());

        externalLegalCaseConsultationService.createLegalCase(command.title(),command.description(), consultation.getId());

        return consultation.getId();
    }

    @Override
    public void handle(DeleteConsultationCommand command) {
        if (!consultationRepository.existsById(command.consultationId())) {
            throw new IllegalArgumentException("Consultation");
        }
        try {
            consultationRepository.deleteById(command.consultationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting consultation: " + e.getMessage());
        }
    }

    @Override
    public void handle(ChangeConsultationStatusCommand command) {
        var consultation = consultationRepository.findById(command.id());
        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            var payment = externalPaymentConsultationServices.getPaymentById(consultation.get().getPaymentId());
            externalFollowUpConsultationService.createNotification(
                    "Pago Aceptado",
                    "Ahora la consulta se encuentra pagada",
                    payment.get().getClientId(),
                    command.id()
            );
            consultation.get().changeStatus();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while changing consultation status: " + e.getMessage());
        }
    }

    @Override
    public void handle(ApproveConsultationCommand command) {
        var consultation = consultationRepository.findById(command.consultationId());
        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            var payment = externalPaymentConsultationServices.getPaymentById(consultation.get().getPaymentId());
            externalFollowUpConsultationService.createNotification(
                    "Consulta Aceptada",
                    "La consulta ha sido aceptada",
                    payment.get().getClientId(),
                    command.consultationId()
            );
            consultation.get().setApplicationAccepted();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while approving consultation: " + e.getMessage());
        }
    }

    @Override
    public void handle(RejectConsultationCommand command) {
        var consultation = consultationRepository.findById(command.consultationId());
        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            var payment = externalPaymentConsultationServices.getPaymentById(consultation.get().getPaymentId());
            externalFollowUpConsultationService.createNotification(
                    "Consulta Rechazada",
                    "La consulta ha sido rechazada",
                    payment.get().getClientId(),
                    command.consultationId()
            );
            consultation.get().setApplicationDenied();

            externalCommunicationConsultationService.deleteChatRoom(consultation.get().getId());
            externalLegalCaseConsultationService.deleteLegalCaseById(consultation.get().getId());
            externalPaymentConsultationServices.deletePayment(consultation.get().getPaymentId());
            consultationRepository.deleteById(consultation.get().getId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while rejecting consultation: " + e.getMessage());
        }
    }
}
