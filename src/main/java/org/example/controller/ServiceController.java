package org.example.controller;

import org.example.db.EmployeManager;
import org.example.db.ServiceManage;
import org.example.model.Employe;
import org.example.model.Service;
import org.example.view.MessageInterface;
import org.example.view.ServiceInterface;

import java.util.List;
import java.util.Scanner;

public class ServiceController {
    public void createServiceNotification(int employeId){
        Scanner scanner= new Scanner(System.in);

        System.out.println("Quel est le nom de votre service de notifcation ?");
        String nom = scanner.nextLine();

        System.out.println("Description du service");
        String des = scanner.nextLine();

        Service service = new Service(nom,des,employeId);
        ServiceManage.saveService(service);

    }

    public  void affichersServices(int employeId){
        System.out.println("Liste des Services");

        List<Service> services = ServiceManage.afficheService() ;
        for (Service s :services) {
            System.out.println("Nom :" + " " + s.getNom() + " " + "Description :" + " " + s.getDes());
        }
        ServiceInterface.serviceMenu(employeId);
    }

    public void serviceAbonnement(int employeId) {
        Scanner scanner = new Scanner(System.in);
        List<Service> services = ServiceManage.afficheService();

        if (services.isEmpty()) {
            System.out.println("Aucun service disponible pour le moment.");
            return;
        }

        //Récupérer l'objet Employe depuis son ID
        Employe employe = EmployeManager.getEmployeById(employeId); // méthode à créer si elle n’existe pas
        if (employe == null) {
            System.out.println("Employé introuvable.");
            return;
        }

        Service serviceChoisi = null;
        while (true) {
            System.out.println("Selectionner le service à la quel vous voulez-vous abonnée");
            for (Service s : services) {
                System.out.println(String.format("N: %d | Nom : %-20s | Description : %s", s.getId(), s.getNom(), s.getDes()));
            }
            System.out.print("\nEntrez le numero du service auquel vous souhaitez vous abonner (ou 0 pour quitter) : ");

            int choix;
            try {
                choix = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Veuillez entrer un nombre valide.");
                continue;
            }

            if (choix == 0) {
                System.out.println("Retour au menu.");
                break;
            }

            // Vérifie si le service avec cet ID existe
            serviceChoisi = null;
            for (Service s : services) {
                if (s.getId() == choix) {
                    serviceChoisi = s;
                    break;
                }
            }

            if (serviceChoisi != null) {
                //boolean success = ServiceManage.abonnerUtilisateur( employeId, serviceChoisi.getId());
                boolean success = ServiceManage.abonnerUtilisateur(employe, serviceChoisi);
                if (success) {
                    System.out.println("Abonnement réussi au service : " + serviceChoisi.getNom());
                    //MessageInterface.messageMenu(employeId, serviceChoisi.getId());
                } else {
                    System.out.println("Vous êtes déjà abonné à ce service.");
                }
                MessageInterface.messageMenu(employeId, serviceChoisi.getId());
            } else {
                System.out.println("Aucun service trouvé avec l’ID " + choix);
            }
        }
    }

    public void serviceDesabonnee(int employeId){
        Scanner scanner= new Scanner(System.in);
        List<Service> services = ServiceManage.afficheService() ;

        if (services.isEmpty()) {
            System.out.println("Aucun service disponible pour le moment.");
            return;
        }

        //Récupérer l'objet Employe depuis son ID
        Employe employe = EmployeManager.getEmployeById(employeId); // méthode à créer si elle n’existe pas
        if (employe == null) {
            System.out.println("Employé introuvable.");
            return;
        }

        while (true) {
            System.out.println("Selectionner le service à la quel vous voulez vous desabonnée");
            for (Service s : services) {
                System.out.println(String.format("N: %d | Nom : %-20s | Description : %s", s.getId(), s.getNom(), s.getDes()));
            }
            System.out.print("\nEntrez le numero du service auquel vous souhaitez vous abonner (ou 0 pour quitter) : ");

            int choix;
            try {
                choix = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(" Veuillez entrer un nombre valide.");
                continue;
            }

            if (choix == 0) {
                System.out.println("Retour au menu.");
                break;
            }

            // Vérifie si le service avec cet ID existe
            Service serviceChoisi = null;
            for (Service s : services) {
                if (s.getId() == choix) {
                    serviceChoisi = s;
                    break;
                }
            }

            if (serviceChoisi != null) {
                boolean success = ServiceManage.desabonnerUtilisateur(employe, serviceChoisi);
                if (success) {
                    System.out.println("Vous vous etes desabonnée du : " + serviceChoisi.getNom());
                } else {
                    System.out.println("Vous êtes déjà desabonné à ce service.");
                }
            } else {
                System.out.println("Aucun service trouvé avec l’ID " + choix);
            }
        }
    }

    public void listeDesAbonne(int serviceId){

        List<Employe> employes = ServiceManage.listeEmployesParService(serviceId);

        for (Employe e : employes) {
            System.out.println("Nom : " + e.getNom() + " " + e.getPrenom());
        }
    }
}
