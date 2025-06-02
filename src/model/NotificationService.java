package model;

public class NotificationService {
    int id;
    String nom;
    Employe employe;
    Message message;

    public NotificationService(int id, String nom, Employe employe, Message message){
        this.id = id;
        this.nom = nom;
        this.employe = employe;
        this.message = message;
    }
}
