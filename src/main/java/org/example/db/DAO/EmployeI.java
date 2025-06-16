package org.example.db.DAO;

import org.example.Models.Employe;

import java.util.List;

public interface EmployeI {
    Employe authentification(String email);
    void ajouteUtilisateur(Employe employe);
    Employe getEmployeById(int id);
    void desabonneUtilisateur(int employID);
    void abonneUtilisateur(int employID);
    List<Employe> voirLesAbonnees();
}
