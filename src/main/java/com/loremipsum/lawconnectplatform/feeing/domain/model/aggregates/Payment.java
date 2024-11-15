package com.loremipsum.lawconnectplatform.feeing.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.CompletePaymentCommand;
import com.loremipsum.lawconnectplatform.feeing.domain.model.commands.CreatePaymentCommand;
import com.loremipsum.lawconnectplatform.feeing.domain.model.events.PaymentCompletedEvent;
import com.loremipsum.lawconnectplatform.feeing.domain.model.valueObjects.*;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    private Long clientId;

    @Embedded
    private PaymentAmount amount;

    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Embedded
    private Card card;

    public Payment(CreatePaymentCommand command) {
        this.amount=new PaymentAmount(
                command.amount()
                ,Currency.fromId(command.currency())
        );
        this.clientId = command.clientId();
        this.status = PaymentStatus.PENDIENTE;
        this.card = new Card();
    }
    public void updateCard(CompletePaymentCommand command){
        this.card=new Card(command.cardNumber(),command.expirationDate(),command.cvv());
    }
    public void finishProject(){
        this.registerEvent(new PaymentCompletedEvent(this,this.getId()));
    }
}
