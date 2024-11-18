package com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Embeddable
public record EmailAddress(@Email @Size(max = 50) String address) {
    public EmailAddress() { this(null); }
}
