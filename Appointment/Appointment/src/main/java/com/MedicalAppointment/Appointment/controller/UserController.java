package com.MedicalAppointment.Appointment.controller;


import com.MedicalAppointment.Appointment.dto.CreateDto;
import com.MedicalAppointment.Appointment.model.UserModel;
import com.MedicalAppointment.Appointment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;


    @PostMapping("/appointment/create")
    public ResponseEntity create(@RequestBody CreateDto createDto, @AuthenticationPrincipal UserModel userModel){
        Optional<UserModel> userModel1 = this.userRepository.findById(createDto.idUser());
        if (userModel1.get().getQuantity() == 1){return ResponseEntity.status(HttpStatus.CONFLICT).body("There is already an appointment scheduled!");}
        if (this.)






       return ResponseEntity.badRequest().build();
    }
}
