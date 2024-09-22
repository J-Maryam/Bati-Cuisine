package repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComposantRepository<T> {
    int addComposant(T composant,UUID projetId);
    List<T> getComposantsByProjet(UUID projetId);
}
