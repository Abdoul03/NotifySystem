package org.example.controller;

import org.example.db.MessageManager;
import org.example.interfaces.MessageI;
import org.example.model.Message;


import java.util.Scanner;

public class MessageService implements MessageI {

    @Override
    public void sendMessage(int employId, int serviceId ,String to, String subject, String content) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("quel est votre message");
        String contenue = scanner.nextLine();

        Message message = new Message(contenue, employId,serviceId);
        new  MessageManager().envoyerMessageAuService(message);

    }

    @Override
    public void afficheMessa(int employId) {
        new MessageManager().getEmployNotification(employId);
    }

}
