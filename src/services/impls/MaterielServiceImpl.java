package services.impls;

import models.entities.Composant;
import models.entities.Projet;
import repository.ComposantRepository;
import models.entities.Materiel;
import services.ComposantService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @Override
    public double CalculateCoutComposant(List<Materiel> materiels, Projet projet) {
        return projet.getComposants().stream()
                .filter(c -> c instanceof Materiel)
                .mapToDouble(c -> {
                    Materiel m = (Materiel) c;
                    return (m.getCoutUnitaire() * m.getQuantite() * m.getCoefficientQualite()) + m.getCoutTransport();
                })
                .sum();
    }
}
