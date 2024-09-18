package services.interfaces;

import models.entities.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IClientService {
    int addClient(Client client);
    int updateClient(Client client);
    Optional<Client> getClientById(String clientId);
    int deleteClient(UUID id);
    Optional<Client> getClientByName(String nom);
    List<Client> getAllClients();
}
