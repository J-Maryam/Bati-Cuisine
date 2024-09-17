package models.entities;

import models.enums.TypeComposant;

import java.util.UUID;

public class Composant {
    private UUID id;
    private String nom;
    private float tauxTVA;
    private TypeComposant typeComposant;
    private Projet projet;

    public Composant() {}
    public Composant(UUID id, String nom, float tauxTVA,TypeComposant typeComposant, Projet projet) {
        this.id = id;
        this.nom = nom;
        this.tauxTVA = tauxTVA;
        this.typeComposant = typeComposant;
        this.projet = projet;
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

    public float getTauxTVA() {
        return tauxTVA;
    }

    public void setTauxTVA(float tauxTVA) {
        this.tauxTVA = tauxTVA;
    }

    public TypeComposant getTypeComposant() {
        return typeComposant;
    }

    public void setTypeComposant(TypeComposant typeComposant) {
        this.typeComposant = typeComposant;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }
}
