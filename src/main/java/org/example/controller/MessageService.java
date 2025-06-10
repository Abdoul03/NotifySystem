package org.example.controller;

import org.example.db.MessageManager;
import org.example.interfaces.MessageI;
import org.example.model.Message;


import java.util.Scanner;

public  class MessageService implements MessageI {

    @Override
    public void sendMessage(int employId, int serviceId) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("quel est votre message");
        String contenue = scanner.nextLine();

        Message message = new Message(contenue, employId,serviceId);
        MessageManager.envoyerMessageAuService(message);

    }
    public void afficherMessageEmployer(int employId){
        MessageManager.getEmployNotification(employId);
    }
}
