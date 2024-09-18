package dao.interfaces;

import models.entities.Client;

import java.util.List;
import java.util.UUID;

public interface IClientDao {
    int addClient(Client client);
    int updateClient(Client client);
    int deleteClient(UUID id);
    Client getClientByName(String nom);
    List<Client> getClients();
}