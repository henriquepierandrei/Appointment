package com.MedicalAppointment.Appointment.controller;

import com.MedicalAppointment.Appointment.dto.EmailDto;
import com.MedicalAppointment.Appointment.dto.LoginDto;
import com.MedicalAppointment.Appointment.dto.RegisterDto;
import com.MedicalAppointment.Appointment.dto.ResponseDto;
import com.MedicalAppointment.Appointment.infra.security.TokenService;
import com.MedicalAppointment.Appointment.model.EmailModel;
import com.MedicalAppointment.Appointment.model.UserModel;
import com.MedicalAppointment.Appointment.repository.EmailRepository;
import com.MedicalAppointment.Appointment.repository.UserRepository;
import com.MedicalAppointment.Appointment.service.CpfValidatorService;
import com.MedicalAppointment.Appointment.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final CpfValidatorService cpfValidatorService;
    private final EmailService emailService;
    private final EmailRepository emailRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto){
        UserModel userModel = this.userRepository.findByEmail(loginDto.email()).orElseThrow(() -> new RuntimeException("User Not Found"));
        if (passwordEncoder.matches(loginDto.password(),userModel.getPassword())){
            String token = this.tokenService.generateToken(userModel);
            return ResponseEntity.ok(new ResponseDto(userModel.getName(),userModel.getEmail(),token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        Optional<UserModel> userModelOptional = this.userRepository.findByEmail(registerDto.email());
        if (userModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use!");
        }

//        UserModel newUser = new UserModel();
//        newUser.setName(registerDto.name());
//        newUser.setLastName(registerDto.lastName());

        if (cpfValidatorService.isValidCpf(registerDto.cpf())){
            String verificationCode = this.emailService.code();
            EmailModel emailModel = new EmailModel();
            emailModel.setEmail(registerDto.email());
            emailModel.setCode(verificationCode);
            emailModel.setName(registerDto.name());
            emailModel.setPassword(registerDto.password());
            emailModel.setLastName(registerDto.lastName());
            emailModel.setNumberWhatsapp(registerDto.number());
            emailModel.setCode(verificationCode);
            emailModel.setYearOld(registerDto.yearOld());
            this.emailRepository.save(emailModel);

            // Enviar email ao usuário
            this.emailService.sendEmail(registerDto.email(), verificationCode);

            return ResponseEntity.ok("Check your email for verification instructions!");
        }

        return ResponseEntity.badRequest().body("Cpf is invalid!");
    }


    @PostMapping("/register/validator")
    public ResponseEntity<ResponseDto> validatorEmail(@RequestBody EmailDto emailDto) {
        System.out.println("Received request with code: " + emailDto.code());

        Optional<EmailModel> emailModelOptional = this.emailRepository.findByCode(emailDto.code());

        if (emailModelOptional.isPresent()) {
            EmailModel emailModel = emailModelOptional.get();
            System.out.println("Found EmailModel: " + emailModel);

            UserModel userModel = new UserModel();
            userModel.setCpf(emailModel.getCpf());
            userModel.setName(emailModel.getName());
            userModel.setLastName(emailModel.getLastName());
            userModel.setYearOld(emailModel.getYearOld());
            userModel.setNumberWhatsapp(emailModel.getNumberWhatsapp());
            userModel.setPassword(passwordEncoder.encode(emailModel.getPassword()));
            userModel.setEmail(emailModel.getEmail());

            this.userRepository.save(userModel);
            this.emailRepository.delete(emailModel);

            return ResponseEntity.ok(new ResponseDto(userModel.getName(), userModel.getEmail(), "User registered successfully"));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }





}
