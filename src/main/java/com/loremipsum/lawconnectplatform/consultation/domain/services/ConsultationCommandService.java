package com.loremipsum.lawconnectplatform.consultation.domain.services;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;

public interface ConsultationCommandService {
    Long handle(CreateConsultationCommand command);
}
