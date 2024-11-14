package com.loremipsum.lawconnectplatform.followup.domain.services;

import com.loremipsum.lawconnectplatform.followup.domain.model.aggregates.Notification;
import com.loremipsum.lawconnectplatform.followup.domain.model.commands.CreateNotificationCommand;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface NotificationCommandService {
    Optional<Notification> handle(CreateNotificationCommand command);
}
