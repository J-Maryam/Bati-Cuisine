package models.entities;

import models.enums.TypeComposant;

import java.util.UUID;

public class MainDOeuvre extends Composant{
    private float tauxHoraire;
    private float heuresTravail;
    private float productiviteOuvrier;

    public MainDOeuvre() {}
    public MainDOeuvre(UUID id, String nom, float tauxTVA, TypeComposant typeComposant, Projet projet, float tauxHoraire, float heuresTravail, float productiviteOuvrier) {
        super(id, nom, tauxTVA, typeComposant, projet);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravail = heuresTravail;
        this.productiviteOuvrier = productiviteOuvrier;
    }

    public float getTauxHoraire() {
        return tauxHoraire;
    }

    public void setTauxHoraire(float tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }

    public float getHeuresTravail() {
        return heuresTravail;
    }

    public void setHeuresTravail(float heuresTravail) {
        this.heuresTravail = heuresTravail;
    }

    public float getProductiviteOuvrier() {
        return productiviteOuvrier;
    }

    public void setProductiviteOuvrier(float productiviteOuvrier) {
        this.productiviteOuvrier = productiviteOuvrier;
    }

    @Override
    public double calculerCout() {
        return getTauxHoraire() * getHeuresTravail() * getProductiviteOuvrier() * getTauxTVA() / 100;
    }
}
