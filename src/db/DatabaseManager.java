package db;

import model.Employe;
import model.Message;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

public class DatabaseManager {
    private static final String URL = new Env().url;
    private static final String USER = new Env().user;
    private static final String PASS = new  Env().passeWord;

    public static void enregistrerEmploye(Employe e) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "INSERT INTO employes (nom, prenom, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, e.getNom());
            stmt.setString(2, e.getPrenom());
            stmt.setString(3, e.getEmail());
            stmt.executeUpdate();
            System.out.println("✅ Employé enregistré en base de données.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void enregistrerMessage(Message m){
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)){
            String sql = "INSERT INTO message(description,dateEnvoi, employe) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, m.getDescription());
            stmt.setString(2,m.getDateEnvoi().toString());
            stmt.setLong(3, m.getEmployeId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
