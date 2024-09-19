package services.impls;

import models.entities.Composant;
import models.entities.Materiel;
import services.interfaces.IComposantService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MaterielService implements IComposantService<Materiel> {

    @Override
    public int addComposant(Materiel composant) {
        return 0;
    }

    @Override
    public int updateComposant(Materiel composant) {
        return 0;
    }

    @Override
    public int deleteComposant(UUID id) {
        return 0;
    }

    @Override
    public Optional<Materiel> getComposantById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<Materiel> getComposants() {
        return List.of();
    }
}
