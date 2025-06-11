package org.example.view;

import org.example.controller.EmployeService;

import java.util.Scanner;

public class UserInterface {
    public  static  void  UserInteracton(int id){
        //Recupperer l'entré de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("1. Creer un service de Notification");
            System.out.println("2. Afficher les service de Notification");
            System.out.println("3. Deconnexion");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // vider la ligne

            switch (choix) {
                case 1:
                    new EmployeService().creeUnService(id);
                    break;
                case 2:
                    new EmployeService().afficheService(id);
                    break;
                case 3:
                    System.out.println("Vous êtes déconnecter !");
                    break;
                default:
                    System.out.println("Choix invalide, essayez encore.");
            }
        }while (choix != 3);
    }
}
