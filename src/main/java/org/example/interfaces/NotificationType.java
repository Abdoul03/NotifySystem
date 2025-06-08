package org.example.interfaces;
import org.example.model.Employe;

public interface NotificationType {
    void envoyerNotification(String message, Employe employe);
}
