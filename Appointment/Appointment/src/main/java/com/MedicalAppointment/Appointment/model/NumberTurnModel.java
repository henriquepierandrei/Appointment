package com.MedicalAppointment.Appointment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NumberTurnModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int numberTurn=0;
}
