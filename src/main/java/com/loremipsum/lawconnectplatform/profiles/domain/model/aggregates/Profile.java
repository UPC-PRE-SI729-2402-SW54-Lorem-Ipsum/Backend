package com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates;

import com.loremipsum.lawconnectplatform.iam.domain.model.aggregates.User;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateClientCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateLawyerCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateProfileCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.EmailAddress;
import com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects.PersonName;
import com.loremipsum.lawconnectplatform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class Profile extends AuditableAbstractAggregateRoot<Profile> {

    private Long userId;

    @Embedded
    private PersonName name;

    @Embedded
    private EmailAddress email;

    @NotNull
    @Size(min= 8, max = 8)
    private String DNI;

    @Size(max = 500)
    @NotNull
    private String image_url;

    @NotNull
    @Size(min = 9, max = 9)
    private String phoneNumber;

    @NotNull
    @Column(name = "address", insertable = false, updatable = false)
    @Size(max = 500)
    private String address;

    public Profile(String firstName, String lastName, String email, String phoneNumber, String DNI, String image_url, String address) {
        this.name = new PersonName(firstName, lastName);
        this.email = new EmailAddress(email);
        this.phoneNumber = phoneNumber;
        this.DNI = DNI;
        this.image_url = image_url;
        this.address = address;
    }

    public Profile(CreateProfileCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.phoneNumber = command.phoneNumber();
        this.address = command.address();
        this.DNI = command.dni();
        this.image_url = command.image_url();
    }

    public Profile(CreateClientCommand command, Long user) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.phoneNumber = command.phoneNumber();
        this.address = command.address();
        this.DNI = command.dni();
        this.image_url = command.image_url();
        this.userId = user;
    }

    public Profile(CreateLawyerCommand command, Long user) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.email = new EmailAddress(command.email());
        this.phoneNumber = command.phoneNumber();
        this.address = command.address();
        this.DNI = command.dni();
        this.image_url = command.image_url();
        this.userId = user;
    }
    public Profile() {

    }
    public Profile updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
        return this;
    }

    public Profile updateEmail(String email) {
        this.email = new EmailAddress(email);
        return this;
    }

    public Profile updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Profile updateDNI(String DNI) {
        this.DNI = DNI;
        return this;
    }

}
