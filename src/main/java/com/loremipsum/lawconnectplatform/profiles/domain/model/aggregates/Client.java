package com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateClientCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer consultationCount;

    @NotNull
    private Integer paidServices;

    @OneToOne
    @JoinColumn(name = "profile", nullable = false)
    private Profile profile;

    public Client() {
        consultationCount = 0;
        paidServices = 0;
    }

    public Client(Profile profile) {
        this();
        this.profile = profile;
    }

    public void incrementConsultationCount() {
        consultationCount++;
    }

    public void incrementPaidServices() {
        paidServices++;
    }
}
