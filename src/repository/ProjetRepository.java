package repository;

import models.entities.Projet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjetRepository {
    UUID addProjet(Projet projet);
    int updateProjet(Projet projet);
    Optional<Projet> getProjetById(UUID projetId);
    List<Projet> getAllProjet();
}
