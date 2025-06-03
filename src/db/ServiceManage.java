package db;

import interfaces.DataBaseManage;
import model.Service;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ServiceManage implements DataBaseManage {
    public static void saveService(Service s){
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)){
            String sql = "INSERT INTO service (nom, des, IdEmploye) VALUE (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, s.getNom());
            stmt.setString(2,s.getDes());
            stmt.setInt(3, s.getIdEmploye());
            //stmt.setArray(4, (Array) s.getMembersId());

            stmt.executeUpdate();
            System.out.println("Service Enregistré");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement : " + e.getMessage());
        }
    }
}
