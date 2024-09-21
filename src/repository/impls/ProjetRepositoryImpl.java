package repository.impls;

import repository.ProjetRepository;
import models.entities.Projet;
import models.enums.EtatProjet;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjetRepositoryImpl implements ProjetRepository {

    private final Connection connection;

    public ProjetRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UUID addProjet(Projet projet) {
        if (projet.getEtatProjet() == null) {
            projet.setEtatProjet(EtatProjet.ENCOURS);
        }
        String sql = "insert into projets (nom, surface, margeBeneficiaire, coutTotal, etatProjet, clientId) values(?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, projet.getNom());
            ps.setDouble(2, projet.getSurface());
            ps.setDouble(3, projet.getMargeBeneficiaire());
            ps.setDouble(4, projet.getCoutTotal());
            ps.setObject(5, projet.getEtatProjet().name(), java.sql.Types.OTHER);
            ps.setObject(6, projet.getClient().getId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        projet.setId(generatedKeys.getObject(1, UUID.class));
                        return projet.getId();
                    }
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return projet.getId();
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
