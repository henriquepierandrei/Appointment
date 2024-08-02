package com.MedicalAppointment.Appointment.repository;

import com.MedicalAppointment.Appointment.model.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailRepository extends JpaRepository<EmailModel,Long> {
    Optional<EmailModel> findByCode(String code);
}
