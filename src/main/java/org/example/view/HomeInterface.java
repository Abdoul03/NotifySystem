package org.example.view;

import org.example.Controller.EmployeController;
import org.example.Models.Employe;

import java.util.Scanner;

public class HomeInterface {
    public static void home(){
        //Recupperer l'entré de l'utilisateur=:
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Créer un compte");
            System.out.println("2. Se connecter");
            System.out.println("3. Quitter");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // vider la ligne
            switch (choix){
                case 1:
                    System.out.println("Quel est votre nom ?");
                    String nom = scanner.nextLine();

                    System.out.println("Quel est votre prenom ?");
                    String prenom = scanner.nextLine();

                    System.out.println("Quel est votre email ?");
                    String email = scanner.nextLine();
                    //Creer un  nouveau objet employer
                    Employe employe = new Employe(nom, prenom, email);
                    new EmployeController().enregistreEmploye(employe);
                    break;
                case 2:
                    System.out.println("Donne votre email");
                    String emailAu = scanner.nextLine();
                    new EmployeController().connexion(emailAu);
                    break;
                case 3:
                    System.out.println("Merci d’avoir utilisé notre système !");
                    break;
                default:
                    System.out.println("Choix invalide, essayez encore.");
            }
        }while (choix != 3);
    }
}
