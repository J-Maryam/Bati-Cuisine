package services.impls;

import dao.interfaces.IProjetDao;
import models.entities.Projet;
import services.interfaces.IProjetService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjetService implements IProjetService {
    private IProjetDao projetDao;
    public ProjetService(IProjetDao projetDao) {
        this.projetDao = projetDao;
    }

    @Override
    public UUID addProjet(Projet projet) {
        return projetDao.addProjet(projet);
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
