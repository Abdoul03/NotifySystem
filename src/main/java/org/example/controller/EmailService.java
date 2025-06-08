package org.example.controller;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;


public class EmailService {

        public static void sendEmail(String to, String subject, String content) {
            final String from = "samabdoul03@gmail.com";
            final String password = "sciu elmo ebks ibxp"; // Utilise un mot de passe d'application Gmail

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
