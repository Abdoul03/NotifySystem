package org.example.view;

import org.example.controller.EmployeService;

import java.util.Scanner;

public class HomeInterface {
    public static void home(){
        //Recupperer l'entré de l'utilisateur=:
        EmployeService employeService = new EmployeService();
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
                    employeService.saveEmployInformation();
                    break;
                case 2:
                    employeService.auth();
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
