package org.example.view;

import org.example.controller.MessageService;
import org.example.controller.ServiceController;

import java.util.Scanner;

public class MessageInterface {
    public  static  void messageMenu(int employeId, int service){
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("\n=== Message Menu ===");
            System.out.println("1. Afficher la liste des employés du service");
            System.out.println("2. Evoyer un message");
            System.out.println("3. Voire mes notification");
            System.out.println("4. retour");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // vider la ligne
            switch (choix){
                case 1:
                    new ServiceController().listeDesAbonne(service);
                    break;
                case 2:
                    new  MessageService().sendMessage(employeId,service);
                    break;
                case 3:
                    System.out.println("En cours");
                    break;
                case 4:
                    System.out.println("retour");
                    break;
                default:
                    System.out.println("Choix invalide, essayez encore.");
            }
        }while (choix != 4);
    }
}
