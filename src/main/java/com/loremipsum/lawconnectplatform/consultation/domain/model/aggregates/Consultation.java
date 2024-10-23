package com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Consultation extends AuditableAbstractAggregateRoot<Consultation> {


    private Long LawyerId;
    private Long ClientId;

    private double priceChat;
    private double priceVideoCall;
    private double priceAppointment;

    public Consultation() {
        this.LawyerId = null;
        this.ClientId = null;
        this.priceChat = 0.0;
        this.priceVideoCall = 0.0;
        this.priceAppointment = 0.0;
    }

    public Consultation(Long lawyerId, Long clientId, double priceChat, double priceVideoCall, double priceAppointment) {
        this();
        this.LawyerId = lawyerId;
        this.ClientId = clientId;
        this.priceChat = priceChat;
        this.priceVideoCall = priceVideoCall;
        this.priceAppointment = priceAppointment;
    }

    public Consultation(CreateConsultationCommand command) {
        this();
        this.LawyerId = command.LawyerId();
        this.ClientId = command.ClientId();
        this.priceChat = command.priceChat();
        this.priceVideoCall = command.priceVideoCall();
        this.priceAppointment = command.priceAppointment();
    }

}
