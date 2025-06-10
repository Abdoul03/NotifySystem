package org.example.db;

import org.example.controller.EmailService;
import org.example.interfaces.DataBaseManage;
import org.example.model.Employe;
import org.example.model.Message;

import java.sql.*;
import java.util.List;


public class MessageManager implements DataBaseManage {
    public static void saveMessage(Message m){
        try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
            String sql = "INSERT INTO message(contenue,dateEnvoi, sender, serviceId) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, m.getContenue());
            stmt.setTimestamp(2, Timestamp.valueOf(m.getDateEnvoi()));
            stmt.setLong(3, m.getEmployeId());
            stmt.setInt(4, m.getServiceId());
            stmt.executeUpdate();
            System.out.println("Message enregistré!");
            // Récupérer les emails des abonnés
            String query = "SELECT e.email FROM employe e " +
                    "JOIN employe_service es ON es.employe_id = e.id " +
                    "WHERE es.service_id = ?";
            PreparedStatement mailStmt = con.prepareStatement(query);
            mailStmt.setInt(1, m.getServiceId());
            ResultSet rs = mailStmt.executeQuery();

            // Envoyer l'email à chaque abonné
            while (rs.next()) {
                String emailDestinataire = rs.getString("email");
                String sujet = "Kounafoli";
                String contenu = m.getContenue() + "\n\nEnvoyé par l'employé ID : " + m.getEmployeId();
                EmailService.sendEmail(emailDestinataire, sujet, contenu);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors d'envoi : " + e.getMessage());
        }
    }

    public static void envoyerMessageAuService(Message m) {
        saveMessage(m); // 1. Enregistre le message

        int messageId = getLastInsertedMessageId(); // 2. Récupérer l’ID du message
        List<Employe> employes = ServiceManage.listeEmployesParService(m.getServiceId()); // 3. Liste abonnés

        if (employes.isEmpty()) {
            System.out.println("Aucun membre abonné au service.");
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO employe_message (message_id, employe_id) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            for (Employe e : employes) {
                stmt.setInt(1, messageId);
                stmt.setInt(2, e.getId());
                stmt.addBatch(); // pour les exécuter en lot
            }
            stmt.executeBatch();
            System.out.println("📨 Message envoyé à tous les membres du service !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la diffusion : " + e.getMessage());
        }
    }

    public static int getLastInsertedMessageId() {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS id FROM message")) {

            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Erreur récupération ID message : " + e.getMessage());
        }
        return -1;
    }

    public static void getEmployNotification(int employeId){
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT m.contenue, m.dateEnvoi, s.nom AS service " +
                    "FROM employe_message em " +
                    "JOIN message m ON m.id = em.message_id " +
                    "JOIN service s ON s.id = m.serviceId " +
                    "JOIN employe e ON e.id = m.sender " +
                    "WHERE em.employe_id = ? " +
                    "ORDER BY m.dateEnvoi DESC";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, employeId);
            ResultSet rs = stmt.executeQuery();

            System.out.println("📨 Messages reçus :");
            boolean hasMessage = false;
            while (rs.next()) {
                hasMessage = true;
                String contenue = rs.getString("contenue");
                Timestamp dateEnvoi = rs.getTimestamp("dateEnvoi");
                String serviceNom = rs.getString("service");
                String senderNom = rs.getString("sender_name");

                System.out.println("──────────────────────────────────");
                System.out.println("👤 De       : " + senderNom);
                System.out.println("📅 Date     : " + dateEnvoi);
                System.out.println("📌 Service  : " + serviceNom);
                System.out.println("✉️ Message  : " + contenue);
            }

            if (!hasMessage) {
                System.out.println("Aucun message reçu.");
            }

        } catch (Exception e) {
            System.out.println("❌ Erreur récupération des messages : " + e.getMessage());
        }
    }
}
