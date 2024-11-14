package com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.CreateLegalCaseCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.valueobjects.Documents;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.valueobjects.LegalCaseStatus;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Entity
public class LegalCase extends AuditableAbstractAggregateRoot<LegalCase> {

    @Column(nullable = false)
    @Size(max = 120)
    private String title;

    @Column(nullable = false)
    @Size(max = 120)
    private String description;

    @Column(nullable = false)
    private LegalCaseStatus status;

    @NotNull
    private Long consultationId;

    @Embedded
    private Documents documents;

    protected LegalCase() {
        this.documents = new Documents();
        this.status = LegalCaseStatus.OPEN;
    }


    public LegalCase(CreateLegalCaseCommand command) {
        this();
        this.title = command.title();
        this.description = command.description();
        this.consultationId = command.consultationId();
    }

    public void close() {
        this.status = LegalCaseStatus.CLOSED;
    }

}