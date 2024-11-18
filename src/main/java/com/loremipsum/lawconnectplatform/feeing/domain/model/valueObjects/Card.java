package com.loremipsum.lawconnectplatform.feeing.domain.model.valueObjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import org.apache.logging.log4j.util.Strings;

import java.time.LocalDate;

@Embeddable
public record Card(@Size(min=16, max = 16) String cardNumber, LocalDate expirationDate, @Size(min = 3, max = 3) String cvv) {
    public Card() {
        this(Strings.EMPTY,null,Strings.EMPTY);
    }
}
