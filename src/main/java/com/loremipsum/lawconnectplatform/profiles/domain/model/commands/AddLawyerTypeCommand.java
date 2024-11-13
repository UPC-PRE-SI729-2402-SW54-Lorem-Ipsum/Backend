package com.loremipsum.lawconnectplatform.profiles.domain.model.commands;

public record AddLawyerTypeCommand (
        Long lawyerId,
        Integer lawyerTypeId
) {
}
