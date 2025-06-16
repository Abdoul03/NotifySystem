package org.example.db.DAO;

import java.util.List;

public interface Notification {
  void send(String to, String message, String subject, int employId);
  List<org.example.Models.Notification> voirNotification(int employeId);
  boolean estAbonne(int employeId);
}
