package controller;

import db.MessageManager;
import interfaces.MessageI;
import model.Message;


import java.util.Scanner;

public  class MessageService implements MessageI {


    @Override
    public void sendMessage(int employId, int serviceId) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("quel est votre message");
        String contenue = scanner.nextLine();


        Message message = new Message(contenue, employId);
        MessageManager.saveMessage(message);

    }
}
