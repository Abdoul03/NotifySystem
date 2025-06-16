package org.example.db.DAO.Implementation;

import org.example.Controller.EmailControlleur;
import org.example.Interface.NotificationI;
import org.example.Models.Notification;
import org.example.Service.NotificationService;
import org.example.db.MysqlConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NotificatonDAO implements org.example.db.DAO.Notification {
    private Connection con = MysqlConnexion.getInstance().con;

    @Override
    public void send(String to, String message, String subject, int employId){
        try {
            //Enregistre une notification
            String sql = "INSERT INTO notification (contenue, dateEnvoie, idEmploye) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, message);
            stmt.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(3, employId);
            stmt.executeUpdate();
            System.out.println("Message enregistré!");

            // Récupérer les emails des abonnés
            String query = "SELECT * FROM Employe WHERE estAbonne = true and id != ?";
            PreparedStatement mailStmt = con.prepareStatement(query);
            mailStmt.setInt(1, employId);
            ResultSet rs = mailStmt.executeQuery();
            // Envoyer l'email à chaque abonné
            while (rs.next()) {
                String emailDestinataire = rs.getString("email");
                String sujet = "Kounafoli";
                String contenu = message + "\n\nEnvoyé par l'employé ID : " + employId;
                new NotificationService(new EmailControlleur()).notifier(emailDestinataire,contenu,sujet,0);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur enregistrement Notification" + e.getMessage());
        }
    }

    @Override
    // Récupère les notifications si l'utilisateur est abonné
    public List<Notification> voirNotification(int employeId) {
        List<Notification> notifications = new ArrayList<>();

        if (!estAbonne(employeId)) {
            System.out.println("Vous devez être abonné pour voir les notifications.");
            return notifications;
        }

        try {
            String sql = "SELECT * FROM notification WHERE idEmploye != ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, employeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Notification notif = new Notification(
                        rs.getInt("id"),
                        rs.getString("contenue"),
                        rs.getTimestamp("dateEnvoie").toLocalDateTime(),
                        rs.getInt("idEmploye")

                );
                notifications.add(notif);
            }

            System.out.println("Vous avez : " + notifications.size());

        } catch (Exception e) {
            throw new RuntimeException("Erreur récupération notifications : " + e.getMessage());
        }

        return notifications;
    }


    // Vérifie si l'employé est abonné
    @Override
    public boolean estAbonne(int employeId) {
        try {
            String sql = "SELECT estAbonne FROM Employe WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, employeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getBoolean("estAbonne");
            } else {
                throw new RuntimeException("Employé introuvable");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur vérification abonnement : " + e.getMessage());
        }
    }
}
