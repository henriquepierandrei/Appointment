package com.MedicalAppointment.Appointment.controller;

import com.MedicalAppointment.Appointment.dto.ValidatorDto;
import com.MedicalAppointment.Appointment.model.AppointmentModel;
import com.MedicalAppointment.Appointment.model.CloseAppointmentsModel;
import com.MedicalAppointment.Appointment.model.NumberTurnModel;
import com.MedicalAppointment.Appointment.model.UserModel;
import com.MedicalAppointment.Appointment.repository.AppointmentRepository;
import com.MedicalAppointment.Appointment.repository.IsClosedRepository;
import com.MedicalAppointment.Appointment.repository.NumberTurnRepository;
import com.MedicalAppointment.Appointment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AppointmentRepository appointmentRepository;
    private final NumberTurnRepository numberTurnRepository;
    private final UserRepository userRepository;
    private final IsClosedRepository isClosedRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");



    @GetMapping("/list/appointment/{date}")
    public ResponseEntity getAppointmentByDate(@PathVariable(value = "date") String date){
        List<AppointmentModel> appointmentModelList = this.appointmentRepository.findByDate(date);
        if (appointmentModelList.isEmpty()){return ResponseEntity.badRequest().build();}

        return ResponseEntity.status(HttpStatus.FOUND).body(appointmentModelList);
    }

    @DeleteMapping("finish/appointments/{date}")
    public ResponseEntity<String> deleteAppointments(@PathVariable(value = "date") String date) {
        try {
            List<AppointmentModel> appointmentModels = this.appointmentRepository.findByDate(date);
            System.out.println(date);
            System.out.print(appointmentModels);

            for (int i = 0; i < appointmentModels.size(); i++){
                Optional<UserModel> userModel = this.userRepository.findById(appointmentModels.get(i).getIdUser());
                userModel.get().setQuantity(0);
                this.userRepository.save(userModel.get());
            }


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

    @GetMapping("/validator/code")
    public ResponseEntity validatorCode(@RequestBody ValidatorDto validatorDto){
        Optional<AppointmentModel> appointmentModel = this.appointmentRepository.findByAcessCode(validatorDto.code());
        Optional<UserModel> userModel = this.userRepository.findByCpf(validatorDto.cpf());

        if (appointmentModel.isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Error in Acess Code!");}
        if (userModel.isEmpty()){return ResponseEntity.status(HttpStatus.CONFLICT).body("Error in CPF!");}
        if (String.valueOf(userModel.get().getId()).contains(String.valueOf(appointmentModel.get().getIdUser())) && appointmentModel.get().isConfirmed()==true){

            userModel.get().setQuantity(0);
            this.userRepository.save(userModel.get());
            this.appointmentRepository.delete(appointmentModel.get());
            return ResponseEntity.status(HttpStatus.OK).body("Appointment Valid!");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Appointment Invalid!");

    }


    @PutMapping("/appoiments/close")
    public ResponseEntity<String> closeAppointments(){
        Optional<CloseAppointmentsModel> closeAppointmentsModel = isClosedRepository.findById(Long.valueOf(1));

        if (closeAppointmentsModel.get().isClosed()){
            closeAppointmentsModel.get().setClosed(false);
            this.isClosedRepository.save(closeAppointmentsModel.get());
            return ResponseEntity.ok().build();
        }

        closeAppointmentsModel.get().setClosed(true);
        this.isClosedRepository.save(closeAppointmentsModel.get());
        return ResponseEntity.ok().build();

    }







}
