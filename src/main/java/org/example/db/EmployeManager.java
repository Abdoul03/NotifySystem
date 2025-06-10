package org.example.db;

import org.example.model.Employe;

import java.sql.*;


public class EmployeManager{
    private Connection con = MysqlConnexion.getInstance().con;
    public void saveEmploye(Employe e) {
        try {
            String sql = "INSERT INTO Employe (nom, prenom, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, e.getNom());
            stmt.setString(2, e.getPrenom());
            stmt.setString(3, e.getEmail());

            stmt.executeUpdate();
            System.out.println("Employé enregestré!");
        } catch (SQLException ex) {
            //ex.printStackTrace();
            System.out.println("Erreur lors de l'enregistrement : " + ex.getMessage());
        }
    }

    public Employe authentifierEmploye(String email) {
        Employe employe = null;
        try{
            String sql = "SELECT * FROM Employe WHERE email = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employe = new Employe(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                );
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de la recuperation de l'utilisateur" + e.getMessage());
        }

        return employe;
    }

    public Employe getEmployeById(int id) {
        try{
            String sql = "SELECT * FROM employe WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                Employe employe = new Employe(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email")
                );
                return employe;
            }
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
        return null;
    }

}
