package services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComposantService<T> {
    int addComposant(T composant, UUID projetId);
    int updateComposant(T composant);
    int deleteComposant(UUID id);
    Optional<T> getComposantById(UUID id);
    List<T> getComposants();
}
