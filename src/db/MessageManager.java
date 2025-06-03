package db;

import interfaces.DataBaseManage;
import model.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class MessageManager implements DataBaseManage {
    public static void saveMessage(Message m){
        try(Connection con = DriverManager.getConnection(URL, USER, PASS)){
            String sql = "INSERT INTO message(description,dateEnvoi, employeId) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, m.getContenue());
            stmt.setString(2,m.getDateEnvoi().toString());
            stmt.setLong(3, m.getEmployeId());
            stmt.setLong(4, m.getReceiverID());
            stmt.executeUpdate();
            System.out.println("Message enregistré!");
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }
}
