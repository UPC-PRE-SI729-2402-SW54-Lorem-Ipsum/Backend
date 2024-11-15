package com.loremipsum.lawconnectplatform.communication.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
public class ChatRoom extends AuditableAbstractAggregateRoot<ChatRoom> {

    @OneToOne
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;
}
