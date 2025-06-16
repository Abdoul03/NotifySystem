package org.example.Interface;

public interface NotificationI {
    void send(String to, String message, String subject,int employId);
    void voirNotification(int employId);
}
