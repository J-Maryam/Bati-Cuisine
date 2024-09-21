package services.impls;

import repository.ProjetRepository;
import models.entities.Projet;
import services.ProjetService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjetServiceImpl implements ProjetService {
    private ProjetRepository projetRepository;
    public ProjetServiceImpl(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    @Override
    public UUID addProjet(Projet projet) {
        return projetRepository.addProjet(projet);
    }

    @Override
    public int updateProjet(Projet projet) {
        return 0;
    }

    @Override
    public int deleteProjet(UUID id) {
        return 0;
    }

    @Override
    public Optional<Projet> getProjetById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Projet> getProjetByName(String projectName) {
        return Optional.empty();
    }

    @Override
    public List<Projet> getAllProjet() {
        return List.of();
    }
}
