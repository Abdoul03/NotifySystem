package model;
import java.sql.Date;
import java.time.LocalDateTime;


public class Message {
    private int id;
    private String contenue;
    private LocalDateTime dateEnvoi;
    private long employeId;

    public Message (String contenue, long employeId){
        this.contenue = contenue;
        this.dateEnvoi = LocalDateTime.now();
        this.employeId = employeId;
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

}
