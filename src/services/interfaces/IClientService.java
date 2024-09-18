package services.interfaces;

import models.entities.Client;

import java.util.List;

public interface IClientService {
    int addClient(Client client);
    int updateClient(Client client);
    int deleteClient(int id);
    Client getClient(int id);
    List<Client> getClients();
}
