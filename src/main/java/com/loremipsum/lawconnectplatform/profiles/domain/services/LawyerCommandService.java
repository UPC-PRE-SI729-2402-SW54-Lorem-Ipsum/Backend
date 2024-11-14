package com.loremipsum.lawconnectplatform.profiles.domain.services;

import com.loremipsum.lawconnectplatform.profiles.domain.model.aggregates.Lawyer;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.AddLawyerPricesCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.AddLawyerTypeCommand;
import com.loremipsum.lawconnectplatform.profiles.domain.model.commands.CreateLawyerCommand;

import java.util.Optional;

public interface LawyerCommandService {
    Optional<Lawyer> handle(CreateLawyerCommand command);
    void handle(AddLawyerPricesCommand command);
    void handle(AddLawyerTypeCommand command);
}
