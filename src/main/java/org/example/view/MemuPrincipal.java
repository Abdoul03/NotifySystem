package org.example.view;

import org.example.Controller.EmployeController;
import org.example.Controller.NotificationController;

import java.util.Scanner;

public class MemuPrincipal {
    public static void menu(int employeID){
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
        System.out.println("\n=== Message Menu ===");
        System.out.println("1. Afficher la liste des employés du service");
        System.out.println("2. Evoyer un message");
        System.out.println("3. Voire mes notification");
        System.out.println("4. S'abonnée au service de Notification");
        System.out.println("5. Se desabonné du service de Notification");
        System.out.println("6. retour");
        System.out.print("Votre choix : ");
        choix = scanner.nextInt();
        scanner.nextLine(); // vider la ligne
            switch (choix){
                case 1:
                    new  EmployeController().ListeAbonne();
                    break;
                case 2:
                    System.out.println("quel est votre message");
                    String contenue = scanner.nextLine();
                    new NotificationController().send(null,contenue,null,employeID);
                    break;
                case 3:
                    new NotificationController().voirNotification(employeID);
                    break;
                case 4:
                    new EmployeController().sucribe(employeID);
                    break;
                case 5:
                    new EmployeController().unscribe(employeID);
                    break;
                case 6:
                    System.out.println("Retour");
                    break;
                default:
                    System.out.println("Choix non disponible");
            }
        }while (choix != 6);
    }
}
