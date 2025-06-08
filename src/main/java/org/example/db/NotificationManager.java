package org.example.db;

import org.example.interfaces.DataBaseManage;
import org.example.model.Notification;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class NotificationManager implements DataBaseManage {

    public static void saveNotification(Notification n){
        try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
            String sql = "INSERT INTO notification(receiverId, messageId) VALUES (?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, n.getMessageId());
            stmt.setInt(2, n.getReceiverId());
            stmt.executeUpdate();
            System.out.println("Notification enregistré!");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }
}
