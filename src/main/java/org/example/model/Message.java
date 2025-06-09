package org.example.model;
import java.sql.Date;
import java.time.LocalDateTime;


public class Message {
    private int id;
    private String contenue;
    private LocalDateTime dateEnvoi;
    private long employeId;
    private int serviceId;

    public Message (String contenue, long employeId, int serviceId){
        this.contenue = contenue;
        this.dateEnvoi = LocalDateTime.now();
        this.employeId = employeId;
        this.serviceId = serviceId;
    }
 /*   public Message (int id, String contenue, long employeId){
        this.id = id;
        this.contenue = contenue;
        this.dateEnvoi = LocalDateTime.now();
        this.employeId = employeId;
    }*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(long employeId) {
        this.employeId = employeId;
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

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
}
