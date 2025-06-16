package org.example.Controller;

import org.example.Models.Employe;
import org.example.db.DAO.Implementation.EmployeDAO;
import org.example.view.MemuPrincipal;

import java.util.List;
import java.util.Scanner;

public class EmployeController {
    private EmployeDAO employeDAO = new EmployeDAO();

    public void enregistreEmploye(Employe employe){
        //Save Employe dans la db
        employeDAO.ajouteUtilisateur(employe);
    }

    public void connexion(String email){

        Employe e = employeDAO.authentification(email);

        if (e != null){
            System.out.println("connexion reussi");
            System.out.println("Bienvenue"+" "+e.getPrenom() + " " +e.getNom());

            MemuPrincipal.menu(e.getId());
        }else {
            System.out.println("Oups Email imtrouvable veuillez creer un compte !");
        }
    }

    public void ListeAbonne(){
        System.out.println("La liste des abonnées");
        List<Employe> abonne = employeDAO.voirLesAbonnees();
        if (abonne == null){
            System.out.println("Aucun Abonné pour le moment");
        }else {
            for (Employe e : abonne){
                System.out.println("Nom :" + " " + e.getNom() + " " + "prenom :" + " " + e.getPrenom());
            }
        }
    }

    public void sucribe(int employeId){
        employeDAO.abonneUtilisateur(employeId);
    }

    public void unscribe(int employeId){
        employeDAO.desabonneUtilisateur(employeId);
    }
}
