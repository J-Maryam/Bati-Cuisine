package dao.impls;

import dao.interfaces.IComposantDao;
import models.entities.Composant;

import java.util.List;
import java.util.Optional;

public class ComposantDao implements IComposantDao {
    @Override
    public int addComposant(Composant composant) {
        return 0;
    }

    @Override
    public int updateComposant(Composant composant) {
        return 0;
    }

    @Override
    public int deleteComposant(int id) {
        return 0;
    }

    @Override
    public Optional<Composant> getComposantById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Composant> getComposants() {
        return List.of();
    }
}
