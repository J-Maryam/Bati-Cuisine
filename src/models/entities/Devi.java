package models.entities;

import java.time.LocalDate;
import java.util.UUID;

public class Devi {

    private UUID id;
    private float montantEstime;
    private LocalDate dateEmission;
    private LocalDate dateValidite;
    private boolean accepte;
    private Projet projet;

    public Devi() {}
    public Devi(UUID id, float montantEstime, LocalDate dateEmission, LocalDate dateValidite, boolean accepte, Projet projet) {
        this.id = id;
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
        this.projet = projet;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public float getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(float montantEstime) {
        this.montantEstime = montantEstime;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public LocalDate getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(LocalDate dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public Projet getProjet() {
        return projet;
    }

    public void setProjet(Projet projet) {
        this.projet = projet;
    }

}
