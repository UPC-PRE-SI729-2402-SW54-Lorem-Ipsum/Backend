package com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.AddLawyerPricesCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.AddLawyerTypeCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateLawyerCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.LawyerType;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.Prices;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
public class Lawyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(targetClass = LawyerType.class)
    @CollectionTable(name = "lawyer_lawyer_type", joinColumns = @JoinColumn(name = "lawyer_id"))
    @Column(name = "lawyer_type_id")
    @Enumerated(EnumType.ORDINAL)
    @Setter
    private Set<LawyerType> lawyerTypes = new HashSet<>();

    @Embedded
    private Prices prices;

    @OneToOne
    @JoinColumn(name = "profile", nullable = false)
    @Setter
    private Profile profile;

    public Lawyer() {
        this.prices = new Prices();
        this.lawyerTypes = new HashSet<>();
    }

    public Lawyer(Profile profile) {
        this();
        this.profile = profile;
    }

    public void setPrices(AddLawyerPricesCommand command) {
        this.prices = new Prices(command.chatPrice(), command.videoCallPrice(), command.faceToFacePrice());
    }

    public void addLawyerType(AddLawyerTypeCommand command) {
        this.lawyerTypes.add(LawyerType.fromId(command.lawyerTypeId()));
    }

}
