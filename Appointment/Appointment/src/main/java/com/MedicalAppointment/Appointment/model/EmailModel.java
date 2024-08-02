package com.MedicalAppointment.Appointment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String code;

    @Size(max = 15, min = 2, message = "Error with name")
    private String name;

    @Size(max = 125, min = 2, message = "Error with lastname")
    private String lastName;

    private String email;
    private String password;
    private String cpf;
    private String yearOld;
    private String numberWhatsapp;

}
