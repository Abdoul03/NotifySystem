package interfaces;
import model.Employe;

public interface NotificationType {
    void envoyerNotification(String message, Employe employe);
}
