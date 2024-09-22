package services;

import models.entities.Composant;
import models.entities.Projet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComposantService<T> {
    int addComposant(T composant, UUID projetId);
    int updateComposant(T composant);
    int deleteComposant(UUID id);
    Optional<T> getComposantById(UUID id);
    List<T> getComposants();
    double CalculateCoutComposant(List<T> composants, Projet projet);

}
