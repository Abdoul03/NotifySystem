package model;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {
    private int id;
    private String description;
    private LocalDateTime dateEnvoi;
    private Long employeId;

    public Message (int id, String description, Long employeId){
        this.id = id;
        this.description = description;
        this.dateEnvoi = LocalDateTime.now();
        this.employeId = employeId;

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

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }


}
