package services.impls;

import models.entities.Composant;
import models.entities.Materiel;
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
    public List<MainDOeuvre> getComposantsByProjet(UUID projetId) {
        List<MainDOeuvre> mainDOeuvres = mainDOeuvreRepository.getComposantsByProjet(projetId);
        return mainDOeuvres;
    }

    @Override
    public double CalculateCoutTotalComposant(List<MainDOeuvre> mainDOeuvres, Projet projet) {
        return mainDOeuvres.stream()
                .mapToDouble(MainDOeuvre::calculerCout)
                .sum();
    }
}
