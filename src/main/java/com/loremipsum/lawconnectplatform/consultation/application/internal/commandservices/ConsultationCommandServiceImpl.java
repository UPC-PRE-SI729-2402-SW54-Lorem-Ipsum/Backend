package com.loremipsum.lawconnectplatform.consultation.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices.ExternalPaymentConsultationServices;
import com.loremipsum.lawconnectplatform.consultation.application.internal.outboundServices.ExternalProfileConsultationService;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.ChangeConsultationStatusCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.DeleteConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationCommandService;
import com.loremipsum.lawconnectplatform.consultation.infrastructure.persistence.jpa.repositories.ConsultationRepository;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ConsultationCommandServiceImpl implements ConsultationCommandService {
    private final ConsultationRepository consultationRepository;
    private final ExternalPaymentConsultationServices externalPaymentConsultationServices;
    private final ExternalProfileConsultationService externalProfileConsultationService;

    public ConsultationCommandServiceImpl(
            ConsultationRepository consultationRepository,
            ExternalPaymentConsultationServices externalPaymentConsultationServices1,
            ExternalProfileConsultationService externalProfileConsultationService1
    ) {
        this.consultationRepository = consultationRepository;
        this.externalPaymentConsultationServices = externalPaymentConsultationServices1;
        this.externalProfileConsultationService = externalProfileConsultationService1;
    }

    @Override
    public Long handle(CreateConsultationCommand command) {

        var lawyer = externalProfileConsultationService.getLawyerById(command.lawyerId());

        var payment = externalPaymentConsultationServices.createPayment(command.clientId(), lawyer.get().getPrices(), command.Currency());

        var consultation = new Consultation(command, payment.get().getId());

        try {
            consultationRepository.save(consultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving consultation: " + e.getMessage());
        }

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
            consultation.get().changeStatus();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while changing consultation status: " + e.getMessage());
        }
    }
}
