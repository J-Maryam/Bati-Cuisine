package services.impls;

import dao.interfaces.IClientDao;
import models.entities.Client;
import services.interfaces.IClientService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientService implements IClientService {
    private IClientDao clientDao;

    public ClientService(IClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public int addClient(Client client) {
        return clientDao.addClient(client);
    }

    @Override
    public int updateClient(Client client) {
        return clientDao.updateClient(client);
    }

    @Override
    public Optional<Client> getClientById(String clientId) {
        try {
            UUID id = UUID.fromString(clientId);
            return clientDao.getClientById(id);
        }catch (IllegalArgumentException e) {
            System.out.println("L'Id fourni n'est valide.");
            return Optional.empty();
        }
    }

    @Override
    public int deleteClient(int id) {
        return 0;
    }

    @Override
    public Client getClient(int id) {
        return null;
    }

    @Override
    public List<Client> getClients() {
        return List.of();
    }
}
