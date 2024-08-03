package com.MedicalAppointment.Appointment.repository;

import com.MedicalAppointment.Appointment.Enum.TimeAppointmentEnum;
import com.MedicalAppointment.Appointment.model.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, Long> {


    // Método para encontrar compromissos por data e hora
    Optional<AppointmentModel> findByDateAndTimeAppointmentEnum(String date, String time);


    List<AppointmentModel> findByDate(String date);
}
