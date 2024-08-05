package com.MedicalAppointment.Appointment.repository;

import com.MedicalAppointment.Appointment.model.CloseAppointmentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IsClosedRepository extends JpaRepository<CloseAppointmentsModel, Long> {
}
