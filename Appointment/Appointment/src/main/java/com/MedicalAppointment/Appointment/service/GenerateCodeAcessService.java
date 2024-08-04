package com.MedicalAppointment.Appointment.service;


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateCodeAcessService {

    public String generateCode(){
        Random random = new Random();
        StringBuilder numberString = new StringBuilder();
        for(int i = 0; i < 6; i++){
            int number = random.nextInt(0,9+1);
            numberString.append(String.valueOf(number));
        }
        return numberString.toString();
    }
}
