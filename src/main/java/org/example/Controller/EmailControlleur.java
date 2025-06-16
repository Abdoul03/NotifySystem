package org.example.Controller;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.example.Interface.NotificationI;

import java.util.Properties;

public class EmailControlleur implements NotificationI {
    @Override
    public void send(String to, String message, String subject, int employId) {
        sendEmail(to, subject, message);
    }

    @Override
    public void voirNotification(int employid) {
    }

    private void sendEmail(String to, String subject, String content) {
        final String from = "samabdoul03@gmail.com";
        final String password = System.getenv().get("emailPasse"); // Utilise un mot de passe d'application Gmail

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Email envoyé avec succès");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
