package com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.events.CreateChatRoomEvent;
import com.loremipsum.lawconnectplatform.consultation.domain.model.events.CreateDefaultPaymentEvent;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.ConsultationStatus;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.ConsultationType;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.LawyerC;
import com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Consultation extends AuditableAbstractAggregateRoot<Consultation> {

    @Embedded
    private LawyerC lawyerId;

    @OneToOne
    @JoinColumn(name = "payment", nullable = false)
    private Payment payment;

    @Column(nullable = false)
    // private ConsultationType consultationStatus;
    private ConsultationStatus consultationStatus;

    @Column(nullable = false)
    private String description;

    private ConsultationType consultationType;


    public Consultation() {
        this.lawyerId = new LawyerC(null);
        this.description = "";
    }

    public Consultation(CreateConsultationCommand command, Payment payment){
        this();
        this.lawyerId = new LawyerC(command.lawyerId());
        this.payment = payment;
        this.consultationStatus = ConsultationStatus.INACTIVE;
        this.description = command.description();
        this.consultationType = ConsultationType.fromId(command.type());
    }

    public Long getLawyerId() {
        return this.lawyerId.lawyerId();
    }

    public Long getPaymentId() {
        return this.payment.getId();
    }

    public void changeStatus() {
        this.consultationStatus = ConsultationStatus.ACTIVE;
    }

    public void createDefaultPayment(Long clientId, Double amount, Integer currency) {
        this.registerEvent(new CreateDefaultPaymentEvent(this, clientId, amount, currency));
    }

    public void createChatRoom(){
        System.out.println("Creating chat room");
        this.registerEvent(new CreateChatRoomEvent(this, this.getId()));
        System.out.println("Chat room created");
    }
}
