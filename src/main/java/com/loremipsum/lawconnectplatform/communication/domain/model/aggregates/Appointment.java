package com.loremipsum.lawconnectplatform.communication.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.communication.domain.model.commands.CreateAppointmentCommand;
import com.loremipsum.lawconnectplatform.communication.domain.model.valueobjects.CommunicationStatus;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    @OneToOne
    @JoinColumn(name = "consultation", nullable = false)
    private Consultation consultation;

    @NotNull
    @Size(max = 500)
    private String description;

    @Size(max = 500)
    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationStatus status;

    public Appointment(CreateAppointmentCommand command, Consultation consultation) {
        this.consultation = consultation;
        this.description = command.description();
        this.location = command.location();
        this.status = CommunicationStatus.PENDING;
    }

    public Appointment() {

    }

    public void setStatus(Integer status) {
        this.status = CommunicationStatus.fromId(status);
    }
}
