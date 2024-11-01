package com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.ConsultationType;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.LawyerC;
import com.loremipsum.lawconnectplatform.consultation.domain.model.valueobjects.ClientC;
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
    private ClientC clientId;

    @Embedded
    private PaymentC paymentId;

    @Column(nullable = false)
    private ConsultationType consultationType;

    @Column(nullable = false)
    private String description;


    public Consultation() {
        this.lawyerId = new LawyerC(null);
        this.clientId = new ClientC(null);
        this.paymentId = new PaymentC(null);
        this.consultationType = ConsultationType.NONE;
        this.description = "";
    }

    public Consultation(Long lawyerId, Long clientId, Long paymentId, String consultationType, String description){
        this();
        this.lawyerId = new LawyerC(lawyerId);
        this.clientId = new ClientC(clientId);
        this.paymentId = new PaymentC(paymentId);
        this.consultationType = ConsultationType.valueOf(consultationType);
        this.description = description;
    }

    public Consultation(CreateConsultationCommand command){
        this();
        this.lawyerId = new LawyerC(command.lawyerId());
        this.clientId = new ClientC(command.clientId());
        this.paymentId = new PaymentC(command.paymentId());
        this.consultationType = ConsultationType.valueOf(command.consultationType());
        this.description = command.description();
    }

    public Long getLawyerId() {
        return this.lawyerId.lawyerId();
    }

    public Long getClientId() {
        return this.clientId.clientId();
    }

    public Long getPaymentId() {
        return this.paymentId.paymentId();
    }

}
