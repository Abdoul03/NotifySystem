package org.example.db;

import org.example.interfaces.DataBaseManage;
import org.example.model.Employe;
import org.example.model.Service;

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
        }catch (Exception ex){
            System.out.println("Erreur lors de l'affichage : " + ex.getMessage());
        }
        return services;
    }

    public static boolean abonnerUtilisateur(Employe e, Service s){
        try(Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            // Vérifier si l’abonnement existe déjà
            String sql = "SELECT * FROM employe_service WHERE employe_id = ? AND service_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, e.getId());
            stmt.setInt(2, s.getId());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return false; // déjà abonné
            }
            //ajouter l'utilisateur a un service
            String insertSql = "INSERT INTO employe_service (employe_id, service_id) VALUES (?, ?)";
            PreparedStatement stmtI = con.prepareStatement(insertSql);
            stmtI.setInt(1, e.getId());
            stmtI.setInt(2, s.getId());
            stmtI.executeUpdate();

            System.out.println(" Utilisateur abonné avec succès !");
            return true;
        }catch (Exception ex){
            System.out.println("Erreur lors l'abonnement : " + ex.getMessage());
            return false;
        }
    }

    public static boolean desabonnerUtilisateur(Employe e, Service s) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "DELETE FROM employe_service WHERE employe_id = ? AND service_id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, e.getId());
            stmt.setInt(2, s.getId());
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (Exception ex) {
            System.out.println("Erreur lors du désabonnement : " + ex.getMessage());
            return false;
        }
    }

    public static List<Employe> listeEmployesParService(int serviceId) {
        List<Employe> listeEmployes = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT e.* FROM employe_service es " + "JOIN employe e ON es.employe_id = e.id " + "WHERE es.service_id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, serviceId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employe employe = new Employe(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                );
                listeEmployes.add(employe);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des employés : " + e.getMessage());
        }
        return listeEmployes;
    }

}
