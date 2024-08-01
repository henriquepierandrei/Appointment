package com.MedicalAppointment.Appointment.repository;

import com.MedicalAppointment.Appointment.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
}
