package dao.impls;

import dao.interfaces.IProjetDao;
import models.entities.Projet;
import models.enums.EtatProjet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjetDao implements IProjetDao {

    private final Connection connection;

    public ProjetDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addProjet(Projet projet) {
        if (projet.getEtatProjet() == null) {
            projet.setEtatProjet(EtatProjet.ENCOURS);
        }
        String sql = "insert into projets (nom, surface, margeBeneficiaire, coutTotal, etatProjet, clientId) values(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, projet.getNom());
            ps.setDouble(2, projet.getSurface());
            ps.setDouble(3, projet.getMargeBeneficiaire());
            ps.setDouble(4, projet.getCoutTotal());
            ps.setObject(5, projet.getEtatProjet().name(), java.sql.Types.OTHER);
            ps.setObject(6, projet.getClient().getId());
            int rowAffected = ps.executeUpdate();
            if (rowAffected > 0) {
                return rowAffected;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
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
