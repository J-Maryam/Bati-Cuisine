package models.entities;

import models.enums.TypeComposant;

import java.util.UUID;

public class Materiel extends Composant{

    private float coutUnitaire;
    private float quantite;
    private float coutTransport;
    private float coefficientQualite;

    public Materiel() {}
    public Materiel(UUID id, String nom, float tauxTVA, TypeComposant typeComposant, Projet projet, float coutUnitaire, float quantite, float coutTransport,float coefficientQualite) {
        super(id, nom, tauxTVA, typeComposant, projet);
        this.coutUnitaire = coutUnitaire;
        this.quantite = quantite;
        this.coefficientQualite = coefficientQualite;
    }

    public float getCoutUnitaire() {
        return coutUnitaire;
    }

    public void setCoutUnitaire(float coutUnitaire) {
        this.coutUnitaire = coutUnitaire;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public float getCoutTransport(){
        return coutTransport;
    }

    public void setCoutTransport(float coutTransport) {
        this.coutTransport = coutTransport;
    }

    public float getCoefficientQualite() {
        return coefficientQualite;
    }

    public void setCoefficientQualite(float coefficientQualite) {
        this.coefficientQualite = coefficientQualite;
    }

    @Override
    public double calculerCout() {
        return (getCoutUnitaire() * getQuantite() * getCoefficientQualite() * getTauxTVA() / 100) + getCoutTransport() ;
    }

}
