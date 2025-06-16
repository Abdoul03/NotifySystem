package org.example.db.DAO.Implementation;

import org.example.Models.Employe;
import org.example.db.DAO.EmployeI;
import org.example.db.MysqlConnexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeDAO implements EmployeI {
    private Connection con = MysqlConnexion.getInstance().con;

    @Override
    public void ajouteUtilisateur(Employe employe) {
        try {
            String sql = "INSERT INTO Employe (nom, prenom, email, estAbonne) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, employe.getNom());
            stmt.setString(2, employe.getPrenom());
            stmt.setString(3, employe.getEmail());
            stmt.setBoolean(4,employe.isEstAbonne());

            stmt.executeUpdate();
            System.out.println("Employé enregestré!");
        } catch (Exception e) {
            throw new RuntimeException("Erreur ajout employé : " + e.getMessage());
        }
    }

    @Override
    public Employe authentification(String email) {
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
                        rs.getString("email"),
                        rs.getBoolean("estAbonne")
                );
            }

        } catch (Exception e) {
            System.out.println("Erreur lors de la recuperation de l'utilisateur" + e.getMessage());
        }

        return employe;
    }

    @Override
    public Employe getEmployeById(int id) {
        try {
            String sql = "SELECT * FROM Employe WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Employe(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getBoolean("estAbonne")
                );
            }
        } catch (Exception e) {
            System.out.println("Erreur récupération employé : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void abonneUtilisateur(int employID) {
        try {
            String sql = "UPDATE Employe SET estAbonne = true WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, employID);
            stmt.executeUpdate();

            Employe employe = getEmployeById(employID);
            if (employe != null) {
                employe.setEstAbonne(true);
            }
            System.out.println("Vous vous etes abonnée !");
        } catch (Exception e) {
            throw new RuntimeException("Erreur abonnement" + e.getMessage());
        }
    }

    @Override
    public void desabonneUtilisateur(int employID) {
        try {
            String sql = "UPDATE Employe SET estAbonne = false WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, employID);
            stmt.executeUpdate();

            Employe employe = getEmployeById(employID);
            if (employe != null) {
                employe.setEstAbonne(true);
            }
            System.out.println("Employé désabonné !");
        } catch (Exception e) {
            throw new RuntimeException("Erreur desabonnement" + e.getMessage());
        }

    }

    @Override
    public List<Employe> voirLesAbonnees() {
        List<Employe>abonnees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Employe WHERE estAbonne = true";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                Employe employe = new Employe(
                    rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getString("email"),
                        rs.getBoolean("estAbonne")

                );
                abonnees.add(employe);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur affichageEmploye" + e.getMessage());
        }

        return abonnees;
    }
}
