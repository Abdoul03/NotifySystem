package controller;

import db.EmployeManager;
import model.Employe;
import view.UserInterface;

import java.util.Scanner;

public class EmployeService {
    public  void saveEmployInformation(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel est votre nom ?");
        String nom = scanner.nextLine();

        System.out.println("Quel est votre prenom ?");
        String prenom = scanner.nextLine();

        System.out.println("Quel est votre email ?");
        String email = scanner.nextLine();

        //Creer un  nouveau objet employer
        Employe employe = new Employe(nom, prenom, email);

        //Save Employe dans la db
        EmployeManager.saveEmploye(employe);

    }
    public  void auth(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Donne votre email");
        String email = scanner.nextLine();

        Employe e = EmployeManager.authentifierEmploye(email);

        if (e != null){
            System.out.println("connexion reussi");
            System.out.println("Bienvenue"+" "+e.getPrenom() + " " +e.getNom());

            UserInterface.UserInteracton(e.getId());
        }else {
            System.out.println("Oups Email imtrouvable veuillez creer un compte !");
        }


    }
    public void abonne (){

    }

    public void desabonne (){

    }
}
