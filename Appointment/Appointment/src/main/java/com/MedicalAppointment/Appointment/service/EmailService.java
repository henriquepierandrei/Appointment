package com.MedicalAppointment.Appointment.service;

import com.MedicalAppointment.Appointment.model.AppointmentModel;
import com.MedicalAppointment.Appointment.model.UserModel;
import com.MedicalAppointment.Appointment.repository.AppointmentRepository;
import com.MedicalAppointment.Appointment.repository.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final JavaMailSender javaMailSender;

    public void sendEmail(String to, String code) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("Código de Verificação de Duas Etapas");

            // Define o conteúdo da mensagem em HTML
            String htmlContent = "<html>"
                    + "<body>"
                    + "    <h1 style='color: #ffffff; background-color: rgb(31, 110, 228); padding: 5px; border-radius: 5px;text-align:center;'>"
                    + "        Seu Código de Verificação"
                    + "    </h1>"
                    + "    <p style='font-weight: bold;'>Seu código está logo abaixo:</p>"
                    + "    <p style='font-size: 20px; color: #ffffff; background-image: linear-gradient(to right, rgb(48, 46, 46), rgb(58, 53, 53)); padding: 4px; border-radius: 10px 0px;text-align:center;font-size: 2em;'>"
                    + "        <b>" + code + "</b>"
                    + "    </p>"
                    + "        <a href='http://localhost:8080/api/email/send' style=\"text-decoration: none;\"><button style=\"cursor: pointer;background-color:  rgb(14, 96, 218);padding: 10px;border-radius: 5px;font-size: 1.2em;color: white;border: none;margin: 5px auto;display: block;\">✔\uFE0FAutenticar Email</button>\n\n"
                    + "    <div style='background-color: rgb(58, 54, 54); padding: 10px; border-radius: 5px;text-align:center;'>"
                    + "        <p style='background-color: rgb(219, 215, 211); padding: 10px; border-radius: 5px; margin: 0;'>"
                    + "            Por favor, não compartilhe este código com ninguém."
                    + "        </p>"
                    + "    </div>"
                    + "</body>"
                    + "</html>";


            helper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace(); // Trate a exceção adequadamente em sua aplicação
        }
    }

    public String code(){
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 5; i++){
            int number = random.nextInt(0,9+1);
            stringBuilder.append(String.valueOf(number));
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public boolean codeValidator(String code, String codeValidator2, UserModel userModel){
        if (code.contains(codeValidator2)){
            return true;
        }
        return false;
    }


    public void sendEmailNotification(String to, String date, String time, String code, String name){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject("Lembrete da sua Consulta Médica");

            // Define o conteúdo da mensagem em HTML
            String htmlContent = "<html>"
                    + "<body style='font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0;'>"
                    + "    <div style='background-image: linear-gradient(to right, rgb(44, 35, 35), rgb(20, 19, 19)); width: 500px; height: 600px; border-radius: 10px; margin: auto; box-shadow: 2px 5px 10px rgba(0, 0, 0, 0.356); padding: 20px;'>"
                    + "        <h1 style='background-color: rgb(43, 117, 255); color: rgb(255, 255, 255); width: 100%; text-align: center; border-radius: 5px 5px 0px 0px; height: 40px; padding-top: 5px;'>"
                    + "            Notificação da Consulta"
                    + "        </h1>"
                    + "        <div style='width: 95%; height: 400px; margin: auto; padding: 0px;'>"
                    + "            <h2 style='color: rgb(255, 255, 255); text-align: center; width: 80%; margin: auto;'>"
                    + "                Olá, " + name + ", enviamos um e-mail para lembrar a sua consulta!"
                    + "            </h2>"
                    + "            <div style='background-color: rgb(228, 236, 255); width: 85%; height: 300px; margin: auto; border-radius: 5px; padding: 20px;'>"
                    + "                <div style='border-bottom: 2px solid rgb(102, 156, 255);'><h2 style='color: rgb(92, 88, 88);'>📅 " + date + "</h2></div>"
                    + "                <br>"
                    + "                <div style='border-bottom: 2px solid rgb(102, 156, 255);'><h2 style='color: rgb(92, 88, 88);'>🕙 " + time + "</h2></div>"
                    + "                <br>"
                    + "                <div style='border-bottom: 2px solid rgb(102, 156, 255);'><h2 style='color: rgb(92, 88, 88);'>🔒 " + code + "</h2></div>"
                    + "            </div>"
                    + "        </div>"
                    + "        <div style='height: 100px; display: flex; align-items: center; justify-content: center; width: 100%;'>"
                    + "            <button style='border: none; background-color: rgb(73, 137, 255); color: white; width: 70%; padding: 10px; font-size: 1.55em; border-radius: 10px;'>"
                    + "                Confirmar Presença"
                    + "            </button>"
                    + "        </div>"
                    + "    </div>"
                    + "</body>"
                    + "</html>";



            helper.setText(htmlContent, true);

            javaMailSender.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

//(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0 53 10 * * *")
    public void sendEmailNotification() {
        LocalDate dateNow = LocalDate.now();
        String date = dateNow.format(formatter);
        System.out.println("Executing...");


        List<AppointmentModel> appointmentModels = appointmentRepository.findByDate(date.toString());

        for (AppointmentModel appointment : appointmentModels) {
            long id = appointment.getIdUser();
            Optional<UserModel> userModel = this.userRepository.findById(id);
            if (userModel.isPresent()){
                String to = userModel.get().getEmail();
                String date2 = appointment.getDate();
                String time = appointment.getTimeAppointmentEnum().toString();
                String code = appointment.getAcessCode();
                String name = userModel.get().getName();
                sendEmailNotification(to, date2, time, code, name);
            }
        }
    }
}
