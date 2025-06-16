package org.example.Models;

import java.time.LocalDateTime;

public class Notification {
    private int id;
    private String contenue;
    private LocalDateTime dateEnvoi;
    private int idEmploye;


    public Notification(String contenue, LocalDateTime dateEnvoi, int idEmploye) {
        this.contenue = contenue;
        this.dateEnvoi = LocalDateTime.now();;
        this.idEmploye = idEmploye;
    }

    public Notification(int id, String contenue, LocalDateTime dateEnvoi, int idEmploye) {
        this.id = id;
        this.contenue = contenue;
        this.dateEnvoi = LocalDateTime.now();;
        this.idEmploye = idEmploye;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContenue() {
        return contenue;
    }

    public void setContenue(String contenue) {
        this.contenue = contenue;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
}
