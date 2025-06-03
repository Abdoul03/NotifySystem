package db;

import interfaces.DataBaseManage;
import model.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public static List<Service> afficheService(){
       List <Service> services = new ArrayList<>();
        try(Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT * FROM service";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
               Service service = new Service(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("des"),
                        rs.getInt("IdEmploye")
                );
               services.add(service);
            }
        }catch (Exception e){
            System.out.println("Erreur lors de l'affichage : " + e.getMessage());
        }
        return services;
    }
}
