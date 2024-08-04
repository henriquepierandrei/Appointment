package com.MedicalAppointment.Appointment.controller;

import com.MedicalAppointment.Appointment.model.AppointmentModel;
import com.MedicalAppointment.Appointment.model.NumberTurnModel;
import com.MedicalAppointment.Appointment.repository.AppointmentRepository;
import com.MedicalAppointment.Appointment.repository.NumberTurnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AppointmentRepository appointmentRepository;
    private final NumberTurnRepository numberTurnRepository;

    @GetMapping("/list/appointment/{date}")
    public ResponseEntity getAppointmentByDate(@PathVariable(value = "date") String date){
        List<AppointmentModel> appointmentModelList = this.appointmentRepository.findByDate(date);
        if (appointmentModelList.isEmpty()){return ResponseEntity.badRequest().build();}
        return ResponseEntity.status(HttpStatus.FOUND).body(appointmentModelList);
    }

    @DeleteMapping("finish/appointments")
    public ResponseEntity<String> deleteAppointments(@RequestBody String date) {
        try {
            List<AppointmentModel> appointmentModels = this.appointmentRepository.findByDate(date);
            int quantity = 0;
            if (!appointmentModels.isEmpty()) {
                for (AppointmentModel appointmentModel : appointmentModels) {
                    this.appointmentRepository.delete(appointmentModel);
                    quantity++;
                }
                NumberTurnModel numberTurnModel = this.numberTurnRepository.findById(1);
                if (numberTurnModel == null) {
                    return ResponseEntity.notFound().build();
                }
                numberTurnModel.setNumberTurn(numberTurnModel.getNumberTurn() - quantity);
                this.numberTurnRepository.save(numberTurnModel);
                return ResponseEntity.ok("Appointments deleted!");
            }
            return ResponseEntity.badRequest().body("No appointments found for the given date.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
        }
    }


}
