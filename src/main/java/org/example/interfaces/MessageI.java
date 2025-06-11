package org.example.interfaces;

public interface MessageI {
    void sendMessage(int employId, int serviceId ,String to, String subject, String content);
    void afficheMessa(int employId);
}
