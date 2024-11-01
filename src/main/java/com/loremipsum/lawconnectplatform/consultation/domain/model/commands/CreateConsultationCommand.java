package com.loremipsum.lawconnectplatform.consultation.domain.model.commands;

public record CreateConsultationCommand(Long lawyerId, Long clientId,
                                        Long paymentId, String consultationType,
                                        String description) {
}
