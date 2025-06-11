package org.example.view;

import org.example.controller.EmployeService;
import org.example.controller.MessageService;

import java.util.Scanner;

public class MessageInterface {
    public  static  void messageMenu(int employeId, int service){
        //Recupperer l'entré de l'utilisateur
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
                    new EmployeService().abonnerAuServie(service);
                    break;
                case 2:
                    new  MessageService().sendMessage(employeId,service,null,null,null);
                    break;
                case 3:
                    new MessageService().afficheMessa(employeId);
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
