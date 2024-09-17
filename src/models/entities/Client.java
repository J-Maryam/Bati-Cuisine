package models.entities;

import java.util.UUID;

public class Client {
    private UUID id;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean isProfessional;

    public Client(){}
    public Client(UUID id, String nom, String adresse, String telephone, boolean isProfessional) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.isProfessional = isProfessional;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isProfessional() {
        return isProfessional;
    }

    public void setProfessional(boolean isProfessional) {
        this.isProfessional = isProfessional;
    }

}
