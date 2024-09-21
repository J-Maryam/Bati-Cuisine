package services.impls;

import dao.interfaces.IComposantDao;
import models.entities.MainDOeuvre;
import services.interfaces.IComposantService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MainDOeuvreService implements IComposantService<MainDOeuvre> {

    private IComposantDao mainDoeuvreDao;
    public MainDOeuvreService(IComposantDao mainDoeuvreDao) {
        this.mainDoeuvreDao = mainDoeuvreDao;
    }

    @Override
    public int addComposant(MainDOeuvre mainDOeuvre, UUID projetId) {
        return mainDoeuvreDao.addComposant(mainDOeuvre, projetId);
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
