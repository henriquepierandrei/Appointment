package com.MedicalAppointment.Appointment.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @PostMapping("/appointment/create")
    public ResponseEntity create(){






       return ResponseEntity.badRequest().build();
    }
}
