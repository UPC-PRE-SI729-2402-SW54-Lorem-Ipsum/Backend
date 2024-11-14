package com.loremipsum.lawconnectplatform.followup.application.internal.commandservices;

import com.loremipsum.lawconnectplatform.followup.domain.model.aggregates.Notification;
import com.loremipsum.lawconnectplatform.followup.domain.model.commands.CreateNotificationCommand;
import com.loremipsum.lawconnectplatform.followup.domain.services.NotificationCommandService;
import com.loremipsum.lawconnectplatform.followup.infrastructure.persistence.jpa.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {

        var notification = new Notification(command);

        notificationRepository.save(notification);

        return Optional.of(notification);
    }
}
