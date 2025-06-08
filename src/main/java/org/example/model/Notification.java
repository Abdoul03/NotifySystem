package org.example.model;

public class Notification {
    private int id;
    private  int receiverId;
    private  int messageId;

    public  Notification(int id, int receiverId, int messageId){
        this.id= id;
        this.receiverId = receiverId;
        this.messageId = messageId ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }
}