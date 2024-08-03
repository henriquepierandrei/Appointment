package com.MedicalAppointment.Appointment.controller;

import com.MedicalAppointment.Appointment.model.AppointmentModel;
import com.MedicalAppointment.Appointment.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AppointmentRepository appointmentRepository;

    @GetMapping("/list/appointment/{date}")
    public ResponseEntity getAppointmentByDate(@RequestParam(value = "date") String date){
        List<AppointmentModel> appointmentModelList = this.appointmentRepository.findByDate(date);
        if (appointmentModelList.isEmpty()){return ResponseEntity.badRequest().build();}
        return ResponseEntity.status(HttpStatus.FOUND).body(appointmentModelList);
    }

}
