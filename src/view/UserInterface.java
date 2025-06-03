package view;

import controller.ServiceController;

import java.util.Scanner;

public class UserInterface {
    public  static  void  UserInteracton(int id){
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Creer un service de Notification");
        System.out.println("2. S'abonnée a un service de Notification");
        System.out.println("3. Se desabonné d'un service de Notification");
        System.out.println("4. Afficher les service de Notification");

        int choix;

        choix = scanner.nextInt();
        scanner.nextLine(); // vider la ligne

        switch (choix){
            case 1:
                new ServiceController().createServiceNotification(id);
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                System.out.println("Choix invalide, essayez encore.");
        }
    }
}
