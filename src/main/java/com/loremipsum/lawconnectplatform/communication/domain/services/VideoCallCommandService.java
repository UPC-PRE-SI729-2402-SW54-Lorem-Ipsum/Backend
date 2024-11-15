package com.loremipsum.lawconnectplatform.communication.domain.services;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.VideoCall;
import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateVideoCallCommand;

import java.util.Optional;

public interface VideoCallCommandService {
    Optional<VideoCall> handle(CreateVideoCallCommand command);
}
