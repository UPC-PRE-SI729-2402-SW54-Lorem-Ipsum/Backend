package com.loremipsum.lawconnectplatform.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.HashSet;
import java.util.Set;

@Embeddable
public record Prices(
        double chatPrice,
        double videoCallPrice,
        double faceToFacePrice
) {
    public Prices() { this(0, 0, 0); }

    public Prices {
        if (chatPrice < 0)
            throw new IllegalArgumentException("Chat price cannot be negative");
        if (videoCallPrice < 0)
            throw new IllegalArgumentException("Video call price cannot be negative");
        if (faceToFacePrice < 0)
            throw new IllegalArgumentException("Face to face price cannot be negative");
    }

    public Set<String> toStringSet() {
        Set<String> pricesSet = new HashSet<>();
        pricesSet.add("chat: " + chatPrice);
        pricesSet.add("videoCall: " + videoCallPrice);
        pricesSet.add("faceToFace: " + faceToFacePrice);
        return pricesSet;
    }
}
