package org.example.Service;

import org.example.Interface.NotificationI;

public class NotificationService {
    private NotificationI notificationI;


    public NotificationService(NotificationI notificationI) {
        this.notificationI = notificationI;
    }

    public void notifier(String to, String message, String subject, int employId){
        notificationI.send(to, message, subject, employId);
    }

    public void afficheNotification(int employeId){
        notificationI.voirNotification(employeId);
    }
}
