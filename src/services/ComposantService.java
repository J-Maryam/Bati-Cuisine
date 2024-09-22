package services;

import models.entities.Composant;
import models.entities.Projet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ComposantService<T> {
    int addComposant(T composant, UUID projetId);
    List<T> getComposantsByProjet(UUID projetId);

}
