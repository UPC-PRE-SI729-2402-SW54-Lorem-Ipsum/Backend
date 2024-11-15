package com.loremipsum.lawconnectplatform.communication.domain.services;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.VideoCall;
import com.loremipsum.lawconnectplatform.communication.domain.model.queries.GetAllVideoCallsByConsultationId;

import java.util.List;
import java.util.Optional;

public interface VideoCallQueryService {
    List<VideoCall> handle(GetAllVideoCallsByConsultationId query);
}
