package org.example.db;

import org.example.interfaces.DataBaseManage;
import org.example.model.Message;

import java.sql.*;


public class MessageManager implements DataBaseManage {
    public static void saveMessage(Message m){
        try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
            String sql = "INSERT INTO message(contenue,dateEnvoi, sender) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, m.getContenue());
            stmt.setTimestamp(2, Timestamp.valueOf(m.getDateEnvoi()));
            stmt.setLong(3, m.getEmployeId());
            stmt.executeUpdate();
            System.out.println("Message enregistré!");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Erreur lors d'envoi : " + e.getMessage());
        }
    }
}
