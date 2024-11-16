package com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.events.CreateChatRoomEvent;
import com.loremipsum.lawconnectplatform.consultation.domain.model.events.CreateDefaultPaymentEvent;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.ApplicationStatus;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.ConsultationType;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.LawyerC;
import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
public class Consultation extends AuditableAbstractAggregateRoot<Consultation> {

    @Embedded
    private LawyerC lawyerId;

    private Long clientId;

    @OneToMany(mappedBy = "consultation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments = new ArrayList<>();

    @Column(nullable = false)
    @Size(max = 500)
    private String description;

    private ConsultationType consultationType;

    private ApplicationStatus applicationStatus;

    public Consultation() {
        this.lawyerId = new LawyerC(null);
        this.description = "";
    }

    public Consultation(CreateConsultationCommand command) {
        this();
        this.lawyerId = new LawyerC(command.lawyerId());
        this.description = command.description();
        this.consultationType = ConsultationType.fromId(command.type());
        this.applicationStatus = ApplicationStatus.PENDING;
        this.clientId = command.clientId();
    }

    public Long getLawyerId() {
        return this.lawyerId.lawyerId();
    }


    public void setApplicationAccepted() {
        this.applicationStatus = ApplicationStatus.APPROVED;
    }

    public void setApplicationDenied() {
        this.applicationStatus = ApplicationStatus.REJECTED;
    }

    public void createDefaultPayment(Long consultationId, Long clientId, Double amount, Integer currency) {
        this.registerEvent(new CreateDefaultPaymentEvent(this,consultationId, clientId, amount, currency));
    }

    public void createChatRoom() {
        System.out.println("Creating chat room");
        this.registerEvent(new CreateChatRoomEvent(this, this.getId()));
        System.out.println("Chat room created");
    }

    public void addPayment(Payment payment) {
        System.out.println("Adding payment");
        this.payments.add(payment);
    }
}
