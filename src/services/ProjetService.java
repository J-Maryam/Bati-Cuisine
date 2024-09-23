package services;

import models.entities.Projet;

import java.util.Optional;
import java.util.UUID;

public interface ProjetService {
    UUID addProjet(Projet projet);
    int updateProjet(Projet projet);
    Optional<Projet> getProjetById(UUID projetId);
    double calculateCoutTotalAvantMarge(UUID projetId, Projet projet);
    double calculateMargeBeneficiaire(UUID projetId, Projet projet);
    double calculateCoutTotalFinal(UUID projetId, Projet projet);
}
