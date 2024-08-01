package com.MedicalAppointment.Appointment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class AppointmentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long idUser;
    private String date;
    private int numberTurn;
    private String acessCode;

}
