package services.impls;

import dao.interfaces.IClientDao;
import models.entities.Client;
import services.interfaces.IClientService;

import java.util.List;

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
        return 0;
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
