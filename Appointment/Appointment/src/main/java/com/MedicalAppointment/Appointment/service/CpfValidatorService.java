package com.MedicalAppointment.Appointment.service;

import org.springframework.stereotype.Service;

@Service
public class CpfValidatorService {
    public boolean isValidCpf(String cpf) {

        cpf = cpf.replaceAll("[^\\d]", "");


        if (cpf.length() != 11) {
            return false;
        }


        if (cpf.chars().distinct().count() == 1) {
            return false;
        }


        int sum = 0;
        int weight = 10;
        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * weight--;
        }
        int firstDigit = 11 - (sum % 11);
        if (firstDigit > 9) {
            firstDigit = 0;
        }
        if (firstDigit != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Calcula e verifica o segundo dígito verificador
        sum = 0;
        weight = 11;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(cpf.charAt(i)) * weight--;
        }
        int secondDigit = 11 - (sum % 11);
        if (secondDigit > 9) {
            secondDigit = 0;
        }
        return secondDigit == Character.getNumericValue(cpf.charAt(10));
    }

}
