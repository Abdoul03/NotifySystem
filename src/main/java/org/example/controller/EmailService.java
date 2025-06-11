package org.example.controller;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.example.interfaces.MessageI;

import java.util.Properties;


public class EmailService implements MessageI {

        @Override
        public void sendMessage(int employId, int serviceId ,String to, String subject, String content) {

            sendEmail(to, subject, content);
        }

        @Override
        public void afficheMessa(int employId) {
            System.out.println("en cours");
        }

        private void sendEmail(String to, String subject, String content) {
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
