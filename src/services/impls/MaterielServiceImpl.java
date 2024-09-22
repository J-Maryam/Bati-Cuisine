package services.impls;

import models.entities.Composant;
import models.entities.Projet;
import repository.ComposantRepository;
import models.entities.Materiel;
import services.ComposantService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MaterielServiceImpl implements ComposantService<Materiel> {

    private ComposantRepository materielRepository;
    public MaterielServiceImpl(ComposantRepository materielRepository) {
        this.materielRepository = materielRepository;
    }

    @Override
    public int addComposant(Materiel materiel, UUID projetId) {
        return materielRepository.addComposant(materiel, projetId);
    }

    @Override
    public List<Materiel> getComposantsByProjet(UUID projetId) {
        List<Materiel> materiels = materielRepository.getComposantsByProjet(projetId);
        return materiels;
    }
}
