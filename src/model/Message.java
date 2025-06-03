package model;
import java.time.LocalDateTime;


public class Message {
    private int id;
    private String contenue;
    private LocalDateTime dateEnvoi;
    private long employeId;
    private long receiverID;

    public Message (int id, String contenue, long employeId, long receiverID){
        this.id = id;
        this.contenue = contenue;
        this.dateEnvoi = LocalDateTime.now();
        this.employeId = employeId;
        this.receiverID = receiverID;

    }

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

    public long getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(long receiverID) {
        this.receiverID = receiverID;
    }
}
