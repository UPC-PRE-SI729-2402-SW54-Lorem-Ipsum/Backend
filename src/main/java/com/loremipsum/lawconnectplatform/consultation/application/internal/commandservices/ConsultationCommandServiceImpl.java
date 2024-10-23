package com.loremipsum.lawconnectplatform.consultation.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.services.ConsultationCommandService;
import com.loremipsum.lawconnectplatform.consultation.infrastructure.persistence.jpa.repositories.ConsultationRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultationCommandServiceImpl implements ConsultationCommandService {
    private final ConsultationRepository consultationRepository;

    public ConsultationCommandServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Long handle(CreateConsultationCommand command) {
        var lawyerId = command.LawyerId();
        var clientId = command.ClientId();

        if (consultationRepository.existsByClientIdAndLawyerId(lawyerId, clientId)) {
            throw new IllegalArgumentException("Consultation already exists for Lawyer: " + lawyerId + " and Client: " + clientId);
        }

        var consultation = new Consultation(command);

        try {
            consultationRepository.save(consultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving consultation: " + e.getMessage());
        }

        return consultation.getId();
    }
}
