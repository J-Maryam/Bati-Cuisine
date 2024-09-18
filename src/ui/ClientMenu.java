package ui;

import models.entities.Client;
import services.interfaces.IClientService;

import java.util.Optional;
import java.util.Scanner;

public class ClientMenu {
    private IClientService clientService;
    private Scanner scanner = new Scanner(System.in);

    public ClientMenu(IClientService clientService) {
        this.clientService = clientService;
    }

    public void displayMenu() {
        boolean running = true;

        int choice;
        while (running) {
            System.out.println("==== Client Menu ====");
            System.out.println("1. Add new Client");
            System.out.println("2. Update Client");
            System.out.println("3. Search Client");
            System.out.println("4. Show all Clients");
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
                    break;
                case 4:
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

}
