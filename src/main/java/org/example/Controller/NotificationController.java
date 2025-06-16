package org.example.Controller;

import org.example.Interface.NotificationI;
import org.example.Models.Notification;
import org.example.db.DAO.Implementation.NotificatonDAO;

import java.util.List;
import java.util.Scanner;

public class NotificationController implements NotificationI {

    private NotificatonDAO notif = new NotificatonDAO();

    @Override
    public void send(String to, String message, String subject, int employId) {
        notif.send(null,message,null,employId);
    }

    @Override
    public void voirNotification(int employId) {
        List<Notification> notifications = notif.voirNotification(employId);

        for (Notification n : notifications){
            System.out.println("──────────────────────────────────");
            System.out.println("Date     : " + n.getDateEnvoi());
            System.out.println("Message  : " + n.getContenue());
            System.out.println("Envoye par l'employe avec id : " + n.getIdEmploye());
        }
    }
}
