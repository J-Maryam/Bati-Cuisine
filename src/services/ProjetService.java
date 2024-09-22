package services;

import models.entities.Projet;
import java.util.UUID;

public interface ProjetService {
    UUID addProjet(Projet projet);
    int updateProjet(Projet projet);
    double calculateCoutTotalAvantMarge(UUID projetId, Projet projet);
}
