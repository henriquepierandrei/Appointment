package com.MedicalAppointment.Appointment.dto;

import java.util.Date;

public record RegisterDto(String name, String lastName, String email, String password, String cpf, String yearOld,String number) {
}
