package dao.impls;

import dao.interfaces.IClientDao;
import models.entities.Client;

import java.util.List;
import java.util.UUID;

public class ClientDao implements IClientDao {

    @Override
    public int addClient(Client client) {
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
