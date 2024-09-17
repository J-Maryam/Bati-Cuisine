package models.entities;

import models.enums.TypeComposant;

import java.util.UUID;

public class Composant {
    protected UUID id;
    protected String nom;
    protected float tauxTVA;
    protected TypeComposant typeComposant;
    protected Projet projet;

    public Composant() {}
    public Composant(UUID id, String nom, float tauxTVA,TypeComposant typeComposant, Projet projet) {
        this.id = id;
        this.nom = nom;
        this.tauxTVA = tauxTVA;
        this.typeComposant = typeComposant;
        this.projet = projet;
    }

}
