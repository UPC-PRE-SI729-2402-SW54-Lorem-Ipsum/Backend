package com.loremipsum.lawconnectplatform.profiles.domain.model.commands;

public record AddLawyerPricesCommand(
        Long lawyerId,
        double price
) {
}
