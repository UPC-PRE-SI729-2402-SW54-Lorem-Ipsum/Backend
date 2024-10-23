package com.loremipsum.lawconnectplatform.consultation.domain.model.commands;

public record CreateConsultationCommand(Long LawyerId, Long ClientId,
                                        double priceChat, double priceVideoCall,
                                        double priceAppointment) {
}
