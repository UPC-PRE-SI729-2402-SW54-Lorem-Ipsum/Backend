package com.loremipsum.lawconnectplatform.communication.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalFollowUpCommunicationService;
import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalPaymentCommunicationService;
import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.VideoCall;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateVideoCallCommand;
import com.loremipsum.lawconnectplatform.communication.domain.services.VideoCallCommandService;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.VideoCallRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VideoCallCommandServiceImpl implements VideoCallCommandService {

    private final VideoCallRepository videoCallRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;
    private final ExternalFollowUpCommunicationService externalFollowUpCommunicationService;
    private final ExternalPaymentCommunicationService externalPaymentCommunicationService;

    public VideoCallCommandServiceImpl(VideoCallRepository videoCallRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService, ExternalFollowUpCommunicationService externalFollowUpCommunicationService, ExternalPaymentCommunicationService externalPaymentCommunicationService) {
        this.videoCallRepository = videoCallRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
        this.externalFollowUpCommunicationService = externalFollowUpCommunicationService;
        this.externalPaymentCommunicationService = externalPaymentCommunicationService;
    }

    @Override
    public Optional<VideoCall> handle(CreateVideoCallCommand command) {

        var consultation = externalConsultationCommunicationService.getConsultationById(command.consultationId());

        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        var payment = externalPaymentCommunicationService.getPaymentById(consultation.get().getPaymentId());

        var VideoCall = new VideoCall(command, consultation.get());

        videoCallRepository.save(VideoCall);

        externalFollowUpCommunicationService.createNotification(
                "Video Call created",
                command.description(),
                payment.get().getClientId(),
                consultation.get().getId()
        );

        return Optional.of(VideoCall);
    }
}
