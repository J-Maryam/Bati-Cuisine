package services.impls;

import repository.ClientRepository;
import models.entities.Client;
import services.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ClientServiceImpl implements ClientService {
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UUID addClient(Client client) {
        return clientRepository.addClient(client);
    }

    @Override
    public int updateClient(Client client) {
        return clientRepository.updateClient(client);
    }

    @Override
    public Optional<Client> getClientById(String clientId) {
        try {
            UUID id = UUID.fromString(clientId);
            return clientRepository.getClientById(id);
        }catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    @Override
    public int deleteClient(UUID id) {
        return clientRepository.deleteClient(id);
    }

    @Override
    public Optional<Client> getClientByName(String nom) {
        return clientRepository.getClientByName(nom);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.getAllClients();
    }
}
