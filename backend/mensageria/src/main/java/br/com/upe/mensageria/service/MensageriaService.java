package br.com.upe.mensageria.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MensageriaService {

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Value("${spring.mail.password}")
    private String emailPassword;

    public MensageriaService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void enviarResultadoParaMedico(String corpoMensagem, String medicoEmail) {


        System.out.println("Enviando e-mail de: " + emailFrom);


        System.out.println("Senha do e-mail configurada: " + (emailPassword != null && !emailPassword.isEmpty() ? "********" : "Não configurada"));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(medicoEmail);
        message.setSubject("Resultado do Exame");
        message.setText(corpoMensagem);

        // Enviando o e-mail
        emailSender.send(message);
    }
}
