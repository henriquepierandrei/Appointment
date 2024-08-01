package com.MedicalAppointment.Appointment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 15, min = 2, message = "Error with name")
    private String name;

    @Size(max = 125, min = 2, message = "Error with lastname")
    private String lastName;

    private String email;
    private String password;
    private String cpf;
    private String yearOld;
    private boolean isAdmin = false;

}
