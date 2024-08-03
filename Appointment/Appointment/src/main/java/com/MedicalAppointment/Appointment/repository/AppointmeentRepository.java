package com.MedicalAppointment.Appointment.repository;

import com.MedicalAppointment.Appointment.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmeentRepository extends JpaRepository<AppointmentModel, Long> {
}
