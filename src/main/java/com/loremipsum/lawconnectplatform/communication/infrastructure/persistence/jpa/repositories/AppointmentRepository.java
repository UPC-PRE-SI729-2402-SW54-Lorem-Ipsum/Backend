package com.loremipsum.lawconnectplatform.communication.infrastructure.persistence.jpa.repositories;

import com.loremipsum.lawconnectplatform.communication.domain.model.aggregates.Appointment;
import com.loremipsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByConsultation(Consultation consultation);
}