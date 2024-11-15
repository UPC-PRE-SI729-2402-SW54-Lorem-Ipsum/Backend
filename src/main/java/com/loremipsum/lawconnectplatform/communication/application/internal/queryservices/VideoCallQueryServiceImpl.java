package com.loremipsum.lawconnectplatform.communication.application.internal.queryservices;

import com.loremipsum.lawconnectplatform.communication.application.internal.outboundServices.ExternalConsultationCommunicationService;
import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.VideoCall;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllVideoCallsByConsultationId;
import com.loremipsum.lawconnectplatform.communication.domain.services.VideoCallQueryService;
import com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories.VideoCallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoCallQueryServiceImpl implements VideoCallQueryService {

    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;
    private final VideoCallRepository videoCallRepository;

    public VideoCallQueryServiceImpl(ExternalConsultationCommunicationService externalConsultationCommunicationService, VideoCallRepository videoCallRepository) {
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
        this.videoCallRepository = videoCallRepository;
    }

    @Override
    public List<VideoCall> handle(GetAllVideoCallsByConsultationId query) {
        var consultation = externalConsultationCommunicationService.getConsultationById(query.consultationId());
        return videoCallRepository.findAllByConsultation(consultation.get());
    }
}
