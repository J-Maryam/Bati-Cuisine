package services.impls;

import dao.interfaces.IComposantDao;
import models.entities.Composant;
import models.entities.Materiel;
import services.interfaces.IComposantService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MaterielService implements IComposantService<Materiel> {

    private IComposantDao materielDao;
    public MaterielService(IComposantDao materielDao) {
        this.materielDao = materielDao;
    }

    @Override
    public int addComposant(Materiel materiel, UUID projetId) {
        return materielDao.addComposant(materiel, projetId);
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
