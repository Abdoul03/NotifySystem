package controller;

import db.ServiceManage;
import model.Employe;
import model.Service;

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

        System.out.println();
    }
}
