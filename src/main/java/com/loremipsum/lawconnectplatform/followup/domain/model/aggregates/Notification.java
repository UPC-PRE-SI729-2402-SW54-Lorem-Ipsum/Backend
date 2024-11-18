package com.loremipsum.lawconnectplatform.followup.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.followup.domain.model.commands.CreateNotificationCommand;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class Notification extends AuditableAbstractAggregateRoot<Notification> {

    @NotNull
    @Size(max = 250)
    private String title;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    private Long clientId;

    @NotNull
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
