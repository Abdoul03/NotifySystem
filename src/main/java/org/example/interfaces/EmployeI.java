package org.example.interfaces;

public interface EmployeI {
    public void creerEmploye();
    public void authentifier();
    public void afficherEmploye();
    public void abonnement(int employeId);
    public void desabonnement(int employeID);
    public void abonnerAuServie(int serviceId);
    public void creeUnService(int employeId);
    public void afficheService(int employeId);
}
