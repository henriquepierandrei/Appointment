package com.MedicalAppointment.Appointment.repository;

import com.MedicalAppointment.Appointment.model.CloseAppointmentsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IsClosedRepository extends JpaRepository<CloseAppointmentsModel, Long> {
}
