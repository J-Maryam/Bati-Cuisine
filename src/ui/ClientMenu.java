package ui;

import models.entities.Client;
import services.ClientService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class ClientMenu {
    private ClientService clientService;
    private Scanner scanner = new Scanner(System.in);

    public ClientMenu(ClientService clientService) {
        this.clientService = clientService;
    }

    public void displayMenu() {
        boolean running = true;

        int choice;
        while (running) {
            System.out.println("==== Client Menu ====");
            System.out.println("1. Add new Client");
            System.out.println("2. Update Client");
            System.out.println("3. Delete Client");
            System.out.println("4. Search Client");
            System.out.println("5. Show all Clients");
            System.out.println("0. Back to the main menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    updateClient();
                    break;
                case 3:
                    deleteClient();
                    break;
                case 4:
                    searchClientByName();
                    break;
                case 5:
                    getAllClient();
                    break;
                case 0:
                    System.out.println("Exiting client menu...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    public void addClient() {

        System.out.println("== Ajouter un nouveau client ==");

        System.out.print("Nom du client: ");
        String nom = scanner.nextLine();

        System.out.print("Adresse: ");
        String adresse = scanner.nextLine();

        System.out.print("Téléphone: ");
        String telephone = scanner.nextLine();

        System.out.print("Est-ce le client est professionnel? (oui/non): ");
        String reponse = scanner.nextLine();
        boolean estProfessionnel = reponse.equalsIgnoreCase("oui");

        Client client = new Client();
        client.setNom(nom);
        client.setAdresse(adresse);
        client.setTelephone(telephone);
        client.setProfessional(estProfessionnel);

        int isAdded = clientService.addClient(client);

        if (isAdded > 0) {
            System.out.println("Client added successfully.");
        }else {
            System.out.println("Client could not be added.");
        }
    }

    public void updateClient() {
        System.out.println("== Mettre à jour un client ==");

        System.out.print("Entrez l'ID du client à mettre à jour: ");
        String clientId = scanner.nextLine();

        Optional<Client> existingClientOpt = clientService.getClientById(clientId);
        if (existingClientOpt.isPresent()) {
            Client existingClient = existingClientOpt.get();
            System.out.println("Client trouvé: " + existingClient.getNom());

            System.out.print("Nouveau nom: ");
            String newName = scanner.nextLine();
            existingClient.setNom(newName);

            System.out.print("Nouvelle adresse: ");
            String newAddress = scanner.nextLine();
            existingClient.setAdresse(newAddress);

            System.out.print("Nouveau téléphone: ");
            String newPhone = scanner.nextLine();
            existingClient.setTelephone(newPhone);

            int isUpdated = clientService.updateClient(existingClient);
            if (isUpdated > 0) {
                System.out.println("Client updated successfully.");
            }else
                System.out.println("Failed to update client.");
        }
    }

    public void deleteClient() {
        System.out.println("== Supprimer un client ==");

        System.out.print("ID du client à supprimer: ");
        String clientId = scanner.nextLine();

        Optional<Client> existingClientOpt = clientService.getClientById(clientId);
        if (existingClientOpt.isPresent()) {
                clientService.deleteClient(UUID.fromString(clientId));
                System.out.println("Client supprimé avec succès.");
        }else System.out.println("Client non trouvé.");
    }

    public void searchClientByName(){

        System.out.print("Entrez le nom du client à rechercher : ");
        String nom = scanner.nextLine();

        Optional<Client> existingClientOpt = clientService.getClientByName(nom);
        if (existingClientOpt.isPresent()) {
            System.out.println("\n=== Client trouvé : ===");
            System.out.println("Id : " + existingClientOpt.get().getId());
            System.out.println("Nom : " + existingClientOpt.get().getNom());
            System.out.println("Adresse : " + existingClientOpt.get().getAdresse());
            System.out.println("Telephone : " + existingClientOpt.get().getTelephone());
            System.out.println("is Professionnel : " + existingClientOpt.get().isProfessional());
        }else System.out.println("Aucun client trouvé avec le nom " + nom);
    }

    public void getAllClient(){
        System.out.println("== Liste des clients ==");
        List<Client> clients = clientService.getAllClients();

        if (clients.isEmpty()){
            System.out.println("No client found.");
        }else {
            for (Client client : clients) {
                System.out.println("ID: " + client.getId());
                System.out.println("Nom: " + client.getNom());
                System.out.println("Adresse: " + client.getAdresse());
                System.out.println("Téléphone: " + client.getTelephone());
                System.out.println("Professionnel: " + client.isProfessional());
                System.out.println("------------------------------");
            }
        }
    }

}
