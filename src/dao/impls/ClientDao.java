package dao.impls;

import dao.interfaces.IClientDao;
import models.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientDao implements IClientDao {

    private final Connection connection;

    public ClientDao(Connection  connection) {
        this.connection = connection;
    }


    @Override
    public int addClient(Client client) {
        String sql = "insert into clients(nom, adresse, telephone, estProfessionnel) values (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getAdresse());
            ps.setString(3, client.getTelephone());
            ps.setBoolean(4, client.isProfessional());

            int isAdded = ps.executeUpdate();
            if (isAdded > 0) {
                return isAdded;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateClient(Client client) {
        String sql = "update clients set nom = ?, adresse = ?, telephone = ?, estProfessionnel = ? where id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, client.getNom());
            ps.setString(2, client.getAdresse());
            ps.setString(3, client.getTelephone());
            ps.setBoolean(4, client.isProfessional());
            ps.setObject(5, client.getId());

            int isUpdated = ps.executeUpdate();
            if (isUpdated > 0) {
                return isUpdated;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Optional<Client> getClientById(UUID clientId) {
        String sql = "select id, nom, adresse, telephone, estProfessionnel from clients where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1, clientId);

            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    Client client = new Client();
                    client.setId((UUID) rs.getObject("id"));
                    client.setNom(rs.getString("nom"));
                    client.setAdresse(rs.getString("adresse"));
                    client.setTelephone(rs.getString("telephone"));
                    client.setProfessional(rs.getBoolean("estProfessionnel"));
                    return Optional.of(client);
                }
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public int deleteClient(UUID id) {
        return 0;
    }

    @Override
    public Client getClientByName(String nom) {
        return null;
    }

    @Override
    public List<Client> getClients() {
        return List.of();
    }
}
