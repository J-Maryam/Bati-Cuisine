package dao.impls;

import dao.interfaces.IClientDao;
import models.entities.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
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
        return 0;
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
