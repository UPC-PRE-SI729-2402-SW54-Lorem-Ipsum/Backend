package com.loremipsum.lawconnectplatform.communication.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
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

    public VideoCallCommandServiceImpl(VideoCallRepository videoCallRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.videoCallRepository = videoCallRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @Override
    public Optional<VideoCall> handle(CreateVideoCallCommand command) {

        var consultation = externalConsultationCommunicationService.getConsultationById(command.consultationId());

        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        var VideoCall = new VideoCall(command, consultation.get());

        videoCallRepository.save(VideoCall);

        return Optional.of(VideoCall);
    }
}
