package repository.impls;

import models.entities.Client;
import repository.ClientRepository;
import repository.ProjetRepository;
import models.entities.Projet;
import models.enums.EtatProjet;
import services.ClientService;
import services.impls.ClientServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjetRepositoryImpl implements ProjetRepository {

    private final Connection connection;
    private ClientService clientService;

    public ProjetRepositoryImpl(Connection connection, ClientService clientService) {
        this.connection = connection;
        this.clientService = clientService;
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
    public UUID updateProjet(Projet projet) {
        if (projet.getId() == null) {
            throw new IllegalArgumentException("Le projet doit avoir un ID valide pour la mise à jour.");
        }

        String sql = "UPDATE projets SET nom = ?, surface = ?, margeBeneficiaire = ?, coutTotal = ?, etatProjet = ?, clientId = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, projet.getNom());
            ps.setDouble(2, projet.getSurface());
            ps.setDouble(3, projet.getMargeBeneficiaire());
            ps.setDouble(4, projet.getCoutTotal());
            ps.setObject(5, projet.getEtatProjet().name(), java.sql.Types.OTHER);

            if (projet.getClient() != null) {
                ps.setObject(6, projet.getClient().getId());
            } else {
                ps.setNull(6, java.sql.Types.OTHER);
            }

            ps.setObject(7, projet.getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                return projet.getId();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projet.getId();
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
    public Optional<Projet> getProjetByName(String projectName) {
        String sql = "SELECT * FROM projets WHERE nom = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, projectName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Projet projet = new Projet();
                projet.setId((UUID) rs.getObject("id"));
                projet.setNom(projectName);
                projet.setSurface(rs.getFloat("surface"));
                projet.setCoutTotal(rs.getFloat("coutTotal"));
                projet.setMargeBeneficiaire(rs.getFloat("margeBeneficiaire"));
                projet.setEtatProjet(EtatProjet.valueOf(rs.getString("etatProjet")));

                UUID clientId = (UUID) rs.getObject("clientId");
                if (clientId != null) {
                    Optional<Client> clientOptional = clientService.getClientById(clientId.toString());
                    if (clientOptional.isPresent()) {
                        projet.setClient(clientOptional.get());
                    } else {
                        projet.setClient(null);
                    }
                } else {
                    projet.setClient(null);
                }                return Optional.of(projet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
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
                projet.setEtatProjet(EtatProjet.valueOf(rs.getString("etatProjet")));


                UUID clientId = (UUID) rs.getObject("clientId");
                if (clientId != null) {
                    Optional<Client> clientOptional = clientService.getClientById(clientId.toString());
                    if (clientOptional.isPresent()) {
                        projet.setClient(clientOptional.get());
                    } else {
                        projet.setClient(null);
                    }
                } else {
                    projet.setClient(null);
                }

                projets.add(projet);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return projets;
    }
}
