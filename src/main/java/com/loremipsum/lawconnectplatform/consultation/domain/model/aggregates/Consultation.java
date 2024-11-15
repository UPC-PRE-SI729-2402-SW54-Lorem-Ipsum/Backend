package com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.events.CreateDefaultPaymentEvent;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.ConsultationStatus;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.LawyerC;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.PaymentC;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Consultation extends AuditableAbstractAggregateRoot<Consultation> {

    @Embedded
    private LawyerC lawyerId;

    @Embedded
    private PaymentC paymentId;

    @Column(nullable = false)
    // private ConsultationType consultationType;
    private ConsultationStatus consultationType;

    @Column(nullable = false)
    private String description;


    public Consultation() {
        this.lawyerId = new LawyerC(null);
        this.paymentId = new PaymentC(null);
        this.description = "";
    }

    public Consultation(Long lawyerId, Long paymentId, String description){
        this();
        this.lawyerId = new LawyerC(lawyerId);
        this.paymentId = new PaymentC(paymentId);
        this.consultationType = ConsultationStatus.INACTIVE;
        this.description = description;
    }

    public Consultation(CreateConsultationCommand command, Long paymentId){
        this();
        this.lawyerId = new LawyerC(command.lawyerId());
        this.paymentId = new PaymentC(paymentId);
        this.consultationType = ConsultationStatus.INACTIVE;
        this.description = command.description();
    }

    public Long getLawyerId() {
        return this.lawyerId.lawyerId();
    }

    public Long getPaymentId() {
        return this.paymentId.paymentId();
    }

    public void changeStatus() {
        this.consultationType = ConsultationStatus.ACTIVE;
    }

    public void createDefaultPayment(Long clientId, Double amount, Integer currency) {
        this.registerEvent(new CreateDefaultPaymentEvent(this, clientId, amount, currency));
    }

}
