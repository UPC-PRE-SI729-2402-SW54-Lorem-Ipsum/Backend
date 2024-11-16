package com.loremipsum.lawconnectplatform.consultation.domain.services;

import com.loremipsum.lawconnectplatform.consultation.domain.model.commands.*;

public interface ConsultationCommandService {
    Long handle(CreateConsultationCommand command);
    void handle(DeleteConsultationCommand command);
    void handle(ChangeConsultationStatusCommand command);
    void handle(ApproveConsultationCommand command);
    void handle(RejectConsultationCommand command);
    void handle(CreatePaymentByConsultationIdCommand command);
}
