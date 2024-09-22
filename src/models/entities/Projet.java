package models.entities;

import models.enums.EtatProjet;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Projet {
    private UUID id;
    private String nom;
    private float surface;
    private float margeBeneficiaire;
    private float coutTotal;
    private EtatProjet etatProjet;
    private Client client;
    private List<Composant> composants;

    public Projet() {
        this.composants = new ArrayList<>();
    }

    public Projet(UUID id, String nom, float surface, float margeBeneficiaire, float coutTotal, EtatProjet etatProjet, Client client, List<Composant> composants) {
        this.id = id;
        this.nom = nom;
        this.surface = surface;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = coutTotal;
        this.etatProjet = etatProjet;
        this.client = client;
        this.composants = composants;
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

    public float getMargeBeneficiaire() {
        return margeBeneficiaire;
    }

    public void setMargeBeneficiaire(float margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
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

    public void setEtatProjet(EtatProjet etatProjet) {
        this.etatProjet = etatProjet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }
}
