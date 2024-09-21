package repository.impls;

import repository.ClientRepository;
import models.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientRepositoryImpl implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryImpl(Connection  connection) {
        this.connection = connection;
    }


    @Override
    public UUID addClient(Client client) {
        String sql = "insert into clients(nom, adresse, telephone, estProfessionnel) values (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getAdresse());
            ps.setString(3, client.getTelephone());
            ps.setBoolean(4, client.isProfessional());

            int isAdded = ps.executeUpdate();
            if (isAdded > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        UUID clientId = generatedKeys.getObject(1, UUID.class);
                        client.setId(clientId);
                        return clientId;
                    }
                }
            }
            return client.getId();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
        String sql = "delete from clients where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1, id);
            int isDeleted = ps.executeUpdate();
            if (isDeleted > 0) {
                return isDeleted;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Optional<Client> getClientByName(String nom) {
        String sql = "select * from clients where nom = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, nom);
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
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "select * from clients";
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Client client = new Client();
                client.setId((UUID) rs.getObject("id"));
                client.setNom(rs.getString("nom"));
                client.setAdresse(rs.getString("adresse"));
                client.setTelephone(rs.getString("telephone"));
                client.setProfessional(rs.getBoolean("estProfessionnel"));
                clients.add(client);
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return clients;
    }
}
