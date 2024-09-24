package services;

import models.entities.Projet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjetService {
    UUID addProjet(Projet projet);
    int updateProjet(Projet projet);
    Optional<Projet> getProjetById(UUID projetId);
    Optional<Projet> getProjetByName(String projectName);
    List<Projet> getAllProjet();
    double calculateCoutTotalAvantMarge(UUID projetId, Projet projet);
    double calculateMargeBeneficiaire(UUID projetId, Projet projet);
    double calculateCoutTotalFinal(UUID projetId, Projet projet);
}
