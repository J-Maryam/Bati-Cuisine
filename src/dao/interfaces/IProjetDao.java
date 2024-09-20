package dao.interfaces;

import models.entities.Projet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IProjetDao {
    UUID addProjet(Projet projet);
    int updateProjet(Projet projet);
    int deleteProjet(UUID id);
    Optional<Projet> getProjetById(UUID id);
    Optional<Projet> getProjetByName(String projectName);
    List<Projet> getAllProjet();
}
