package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Service {
    private int id;
    private  String nom;
    private String des;
    private int IdEmploye;
    private List<Integer> membersId = new ArrayList<>();


    public Service( String nom, String des, int IdEmploye) {
        this.nom = nom;
        this.des = des;
        this.IdEmploye = IdEmploye;

    }
    public Service( int id,String nom, String des, int IdEmploye) {
        this.id=id;
        this.nom = nom;
        this.des = des;
        this.IdEmploye = IdEmploye;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getIdEmploye() {
        return IdEmploye;
    }

    public void setIdEmploye(int IdEmploye) {
        this.IdEmploye = IdEmploye;
    }

    public List<Integer> getMembersId() {
        return membersId;
    }

    public void setMemberId(List<Integer> membersId) {
        this.membersId = membersId;
    }
}
