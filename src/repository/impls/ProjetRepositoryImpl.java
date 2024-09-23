package repository.impls;

import models.entities.Client;
import repository.ProjetRepository;
import models.entities.Projet;
import models.enums.EtatProjet;

import java.sql.*;
import java.util.ArrayList;
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
        String sql = "UPDATE projets SET nom = ?, surface = ?, margeBeneficiaire = ?, coutTotal = ?, etatProjet = ?, clientId = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, projet.getNom());
            ps.setDouble(2, projet.getSurface());
            ps.setDouble(3, projet.getMargeBeneficiaire());
            ps.setDouble(4, projet.getCoutTotal());
            ps.setObject(5, projet.getEtatProjet().name(), java.sql.Types.OTHER);
            ps.setObject(6, projet.getClient().getId());
            ps.setObject(7, projet.getId());

            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Optional<Projet> getProjetById(UUID projetId) {
        String sql = "SELECT * FROM projets WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, projetId);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    Projet projet = new Projet();
                    projet.setId((UUID) resultSet.getObject("id"));
                    projet.setNom(resultSet.getString("nom"));
                    projet.setSurface(resultSet.getFloat("surface"));
                    projet.setCoutTotal(resultSet.getFloat("coutTotal"));

                    return Optional.of(projet);
                } else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public List<Projet> getAllProjet() {
        List<Projet> projets = new ArrayList<>();
        String sql = "SELECT * FROM projets";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Projet projet = new Projet();
                projet.setId((UUID) rs.getObject("id"));
                projet.setNom(rs.getString("nom"));
                projet.setSurface(rs.getFloat("surface"));
                projet.setMargeBeneficiaire(rs.getFloat("margeBeneficiaire"));
                projet.setCoutTotal(rs.getFloat("coutTotal"));
                projet.setEtatProjet(rs.getObject("etatProjet", EtatProjet.class));
                projet.setClient(rs.getObject("client", Client.class));
                projets.add(projet);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return projets;
    }
}
