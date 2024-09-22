package services.impls;

import models.entities.Composant;
import models.entities.Projet;
import repository.ComposantRepository;
import models.entities.MainDOeuvre;
import services.ComposantService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MainDOeuvreServiceImpl implements ComposantService<MainDOeuvre> {

    private ComposantRepository mainDOeuvreRepository;
    public MainDOeuvreServiceImpl(ComposantRepository mainDoeuvreRepository) {
        this.mainDOeuvreRepository = mainDoeuvreRepository;
    }

    @Override
    public int addComposant(MainDOeuvre mainDOeuvre, UUID projetId) {
        return mainDOeuvreRepository.addComposant(mainDOeuvre, projetId);
    }

    @Override
    public int updateComposant(MainDOeuvre composant) {
        return 0;
    }

    @Override
    public int deleteComposant(UUID id) {
        return 0;
    }

    @Override
    public Optional<MainDOeuvre> getComposantById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<MainDOeuvre> getComposants() {
        return List.of();
    }

    @Override
    public double CalculateCoutComposant(List<MainDOeuvre> mainDOeuvres, Projet projet) {
        return projet.getComposants().stream()
                .filter(c -> c instanceof MainDOeuvre)
                .mapToDouble(c -> {
                    MainDOeuvre m = (MainDOeuvre) c;
                    return m.getTauxHoraire() * m.getHeuresTravail() * m.getProductiviteOuvrier();
                })
                .sum();
    }
}
