package com.MedicalAppointment.Appointment.model;

import com.MedicalAppointment.Appointment.Enum.TimeAppointmentEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long idUser;
    private String date;
    private int numberTurn;
    private String acessCode;
    private String timeAppointmentEnum;

}
