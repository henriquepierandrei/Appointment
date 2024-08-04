package com.MedicalAppointment.Appointment.repository;

import com.MedicalAppointment.Appointment.model.NumberTurnModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberTurnRepository extends JpaRepository<NumberTurnModel,Long> {
}
