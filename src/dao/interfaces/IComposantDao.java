package dao.interfaces;

import models.entities.Composant;
import models.entities.Materiel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IComposantDao<T> {
    int addComposant(T composant);
    int updateComposant(T composant);
    int deleteComposant(UUID id);
    Optional<Materiel> getComposantById(UUID id);
    List<Materiel> getComposants();
}
