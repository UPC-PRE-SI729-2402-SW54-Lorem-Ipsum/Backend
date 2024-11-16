package com.loremipsum.lawconnectplatform.consultation.domain.model.commands;

public record CreatePaymentByConsultationIdCommand(
        Long consultationId,
        Double amount,
        Integer currency
)
{
}
