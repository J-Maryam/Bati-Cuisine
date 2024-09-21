package services.impls;

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
}
