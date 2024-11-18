package com.loremipsum.lawconnectplatform.communication.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateVideoCallCommand;
import com.loremipsum.lawconnectplatform.communication.domain.model.valueobjects.CommunicationStatus;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.valueobjects.DocumentsStatus;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class VideoCall extends AuditableAbstractAggregateRoot<VideoCall> {

    @OneToOne
    @JoinColumn(name = "consultation", nullable = false)
    private Consultation consultation;

    @NotNull
    @Size(max = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationStatus status;

    public VideoCall(CreateVideoCallCommand command, Consultation consultation) {
        this.description = command.description();
        this.status = CommunicationStatus.PENDING;
        this.consultation = consultation;
    }

    public VideoCall() {

    }

    public void setStatus(Integer status) {
        this.status = CommunicationStatus.fromId(status);
    }
}
