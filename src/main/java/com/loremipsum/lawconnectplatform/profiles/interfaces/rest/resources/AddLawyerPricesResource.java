package com.loremipsum.lawconnectplatform.profiles.interfaces.rest.resources;

public record AddLawyerPricesResource(
        Long lawyerId,
        double chatPrice,
        double videoCallPrice,
        double faceToFacePrice
) {
}
