package com.MedicalAppointment.Appointment.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to) {
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
                    + "    <p style='font-size: 20px; color: #ffffff; background-image: linear-gradient(to right, rgb(48, 46, 46), rgb(58, 53, 53)); padding: 4px; border-radius: 10px 0px;text-align:center;'>"
                    + "        <b>" + code() + "</b>"
                    + "    </p>"
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
}
