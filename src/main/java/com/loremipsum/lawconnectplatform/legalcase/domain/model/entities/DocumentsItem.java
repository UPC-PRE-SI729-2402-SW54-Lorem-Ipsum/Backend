package com.loremipsum.lawconnectplatform.legalcase.domain.model.entities;

import com.loremipsum.lawconnectplatform.legalcase.domain.model.aggregates.LegalCase;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.commands.AddDocumentByLegalCaseIdCommand;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.valueobjects.DocumentType;
import com.loremipsum.lawconnectplatform.legalcase.domain.model.valueobjects.DocumentsStatus;
import com.loremipsum.lawconnectplatform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class DocumentsItem extends AuditableModel {

    @Column(nullable = false)
    @Size(max = 120)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType type;

    @Column(nullable = false)
    @Size(max = 500)
    private String description;

    @ManyToOne
    @JoinColumn(name = "legal_case_id")
    private LegalCase legalCase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentsStatus status;

    public DocumentsItem(AddDocumentByLegalCaseIdCommand command, LegalCase legalCase) {
        this.title = command.title();
        this.type = DocumentType.fromId(command.type());
        this.description = command.description();
        this.status = DocumentsStatus.fromId(command.status());
        this.legalCase = legalCase;
    }

    public DocumentsItem() {

    }

    public void setStatus(Integer status) {
        this.status = DocumentsStatus.fromId(status);
    }
}
