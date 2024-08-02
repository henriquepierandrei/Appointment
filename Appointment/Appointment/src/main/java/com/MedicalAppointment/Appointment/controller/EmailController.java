package com.MedicalAppointment.Appointment.controller;

import com.MedicalAppointment.Appointment.dto.EmailDto;
import com.MedicalAppointment.Appointment.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailDto emailDto) {
        emailService.sendEmail(emailDto.to());
        return "Email sent successfully";
    }
}
