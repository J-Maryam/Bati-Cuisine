package services.interfaces;

import models.entities.Composant;
import models.entities.Materiel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IComposantService<T> {
    int addComposant(T composant, UUID projetId);
    int updateComposant(T composant);
    int deleteComposant(UUID id);
    Optional<T> getComposantById(UUID id);
    List<T> getComposants();
}
