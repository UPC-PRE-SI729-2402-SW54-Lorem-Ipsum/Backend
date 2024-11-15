package com.loremipsum.lawconnectplatform.followup.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.followup.domain.model.commands.CreateNotificationCommand;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class Notification extends AuditableAbstractAggregateRoot<Notification> {

    private String title;

    private String description;

    private Long clientId;

    private Long consultationId;

    public Notification(CreateNotificationCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.clientId = command.clientId();
        this.consultationId = command.legalCaseId();
    }

    public Notification() {

    }
}
