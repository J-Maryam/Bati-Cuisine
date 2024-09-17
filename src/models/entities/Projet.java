package models.entities;

import models.enums.EtatProjet;

import java.util.UUID;

public class Projet {
    private UUID id;
    private String nom;
    private float surface;
    private float coutTotal;
    private EtatProjet etatProjet;
    private Client client;

    public Projet() {}

    public Projet(UUID id, String nom, float surface, float coutTotal, EtatProjet etatProjet, Client client) {
        this.id = id;
        this.nom = nom;
        this.surface = surface;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
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

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
        this.surface = surface;
    }

    public float getCoutTotal() {
        return coutTotal;
    }

    public void setCoutTotal(float coutTotal) {
        this.coutTotal = coutTotal;
    }

    public EtatProjet getEtatProjet() {
        return etatProjet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
