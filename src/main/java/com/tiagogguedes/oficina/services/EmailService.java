package com.tiagogguedes.oficina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmailRecuperacaoSenha(String destinatario, String token ) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(destinatario);
        message.setSubject("Recuperação de Senha - Sistema de Oficina");
        message.setText("Olá,\n\n" +
                "Você solicitou a recuperação de senha para o Sistema de Oficina.\n\n" +
                "Use o código a seguir para redefinir sua senha: " + token + "\n\n" +
                "Se você não solicitou esta recuperação, por favor ignore este email.\n\n" +
                "Atenciosamente,\nEquipe Sistema de Oficina");
        
        emailSender.send(message);
    }
}
