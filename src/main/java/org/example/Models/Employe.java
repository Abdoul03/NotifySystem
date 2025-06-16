package org.example.Models;

public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private boolean estAbonne;

    public Employe(String nom, String prenom, String email){
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.estAbonne = true;
    }

    public Employe(int id, String nom, String prenom, String email, boolean estAbonne) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.estAbonne = estAbonne;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEstAbonne() {
        return estAbonne;
    }

    public void setEstAbonne(boolean estAbonne) {
        this.estAbonne = estAbonne;
    }

}
