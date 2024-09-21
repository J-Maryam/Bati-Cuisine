package repository;

import models.entities.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    int addClient(Client client);
    int updateClient(Client client);
    Optional<Client> getClientById(UUID clientId);
    int deleteClient(UUID id);
    Optional<Client> getClientByName(String nom);
    List<Client> getAllClients();
}