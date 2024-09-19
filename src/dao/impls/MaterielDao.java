package dao.impls;

import dao.interfaces.IComposantDao;
import models.entities.Composant;
import models.entities.Materiel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MaterielDao implements IComposantDao<Materiel> {

    private Connection connection;
    public MaterielDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addComposant(Materiel Materiel) {
        return 0;
    }

    @Override
    public int updateComposant(Materiel Materiel) {
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
