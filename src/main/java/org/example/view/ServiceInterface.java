package org.example.view;

import org.example.controller.EmployeService;

import java.util.Scanner;

public class ServiceInterface {
    public  static  void serviceMenu(int employeId){
        //Recupperer l'entré de l'utilisateur
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("\n=== Service Menu ===");
            System.out.println("1. S'abonnée a un service de Notification");
            System.out.println("2. Se desabonné d'un service de Notification");
            System.out.println("3. retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // vider la ligne
            switch (choix){
                case 1:
                    new EmployeService().abonnement(employeId);
                    break;
                case 2:
                    new  EmployeService().desabonnement(employeId);
                    break;
                case 3:
                    System.out.println("retour");
                    break;
                default:
                    System.out.println("Choix invalide, essayez encore.");
            }
        }while (choix != 3);

    }
}
