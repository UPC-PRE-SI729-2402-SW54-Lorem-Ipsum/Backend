package com.loremipsum.lawconnectplatform.consultation.domain.services;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.ChangeConsultationStatusCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.CreateConsultationCommand;
import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.DeleteConsultationCommand;

public interface ConsultationCommandService {
    Long handle(CreateConsultationCommand command);
    void handle(DeleteConsultationCommand command);
    void handle(ChangeConsultationStatusCommand command);
}
