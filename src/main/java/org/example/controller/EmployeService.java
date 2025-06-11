package org.example.controller;

import org.example.db.EmployeManager;
import org.example.db.ServiceManage;
import org.example.interfaces.EmployeI;
import org.example.model.Employe;
import org.example.model.Service;
import org.example.view.MessageInterface;
import org.example.view.ServiceInterface;
import org.example.view.UserInterface;

import java.util.List;
import java.util.Scanner;

public class EmployeService implements EmployeI {
    @Override
    public void creerEmploye() {
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
        new EmployeManager().saveEmploye(employe);
    }

    @Override
    public void authentifier() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Donne votre email");
        String email = scanner.nextLine();

        Employe e = new  EmployeManager().authentifierEmploye(email);

        if (e != null){
            System.out.println("connexion reussi");
            System.out.println("Bienvenue"+" "+e.getPrenom() + " " +e.getNom());

            UserInterface.UserInteracton(e.getId());
        }else {
            System.out.println("Oups Email imtrouvable veuillez creer un compte !");
        }

    }

    @Override
    public void afficherEmploye() {

    }

    @Override
    public void abonnement(int employeId) {
        Scanner scanner = new Scanner(System.in);
        List<Service> services = new ServiceManage().afficheService();

        if (services.isEmpty()) {
            System.out.println("Aucun service disponible pour le moment.");
            return;
        }

        //Récupérer l'objet Employe depuis son ID
        Employe employe = new  EmployeManager().getEmployeById(employeId); // méthode à créer si elle n’existe pas
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
                boolean success = new ServiceManage().abonnerUtilisateur(employe, serviceChoisi);
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

    @Override
    public void desabonnement(int employeId) {
        Scanner scanner= new Scanner(System.in);
        List<Service> services = new ServiceManage().afficheService() ;

        if (services.isEmpty()) {
            System.out.println("Aucun service disponible pour le moment.");
            return;
        }

        //Récupérer l'objet Employe depuis son ID
        Employe employe = new EmployeManager().getEmployeById(employeId); // méthode à créer si elle n’existe pas
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
                boolean success = new ServiceManage().desabonnerUtilisateur(employe, serviceChoisi);
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

    @Override
    public void abonnerAuServie(int serviceId) {
        List<Employe> employes = new ServiceManage().listeEmployesParService(serviceId);

        for (Employe e : employes) {
            System.out.println("Nom : " + e.getNom() + " " + e.getPrenom());
        }
    }

    @Override
    public void creeUnService(int employeId) {
        Scanner scanner= new Scanner(System.in);

        System.out.println("Quel est le nom de votre service de notifcation ?");
        String nom = scanner.nextLine();

        System.out.println("Description du service");
        String des = scanner.nextLine();

        Service service = new Service(nom,des,employeId);
        new ServiceManage().saveService(service);
    }

    @Override
    public void afficheService(int employeId) {
        System.out.println("Liste des Services");

        List<Service> services = new ServiceManage().afficheService() ;
        for (Service s :services) {
            System.out.println("Nom :" + " " + s.getNom() + " " + "Description :" + " " + s.getDes());
        }
        ServiceInterface.serviceMenu(employeId);
    }
}
