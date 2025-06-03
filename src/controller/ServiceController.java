package controller;

import db.ServiceManage;
import model.Employe;
import model.Service;
import view.ServiceInterface;

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
    public  void affichersServices(){
        System.out.println("Liste des Services");

        List<Service> services = ServiceManage.afficheService() ;
        for (Service s :services) {
            System.out.println("Nom :" + " " + s.getNom() + " " + "Description :" + " " + s.getDes());
        }
        ServiceInterface.serviceMenu();
    }
}
