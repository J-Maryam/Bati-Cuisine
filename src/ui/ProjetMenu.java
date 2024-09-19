package ui;

import dao.impls.ClientDao;
import dao.interfaces.IProjetDao;
import models.entities.Client;
import models.entities.Projet;
import models.enums.EtatProjet;
import services.impls.ClientService;
import services.impls.ProjetService;
import services.interfaces.IClientService;
import services.interfaces.IProjetService;

import java.util.Optional;
import java.util.Scanner;

public class ProjetMenu {
    private Scanner scanner = new Scanner(System.in);
    private IProjetService projetService;
    private IClientService clientService;
    private ClientMenu clientMenu;

    public ProjetMenu(IProjetService projetService, IClientService clientService, ClientMenu clientMenu) {
        this.projetService = projetService;
        this.clientService = clientService;
        this.clientMenu = clientMenu;
    }

    public void displayMenu() {
        boolean running = true;

        int choice;
        while (running) {
            System.out.println("==== Project Menu ====");
            System.out.println("1. Add new Project");
            System.out.println("2. Update Project");
            System.out.println("3. Delete Project");
            System.out.println("4. Search Project");
            System.out.println("5. Show all Projects");
            System.out.println("0. Back to the main menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProject();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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

    public void addProject() {
        System.out.println("== Ajouter un nouveau projet ==");

        Client client = null;
        String option;
        Float marge = null;

        do {
            System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
            System.out.println("1. Chercher un client existant");
            System.out.println("2. Ajouter un nouveau client");
            System.out.print("Choisissez une option : ");
            option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.println("--- Recherche de client existant ---");

                System.out.print("Entrer le nom du client à rechercher : ");
                String nomClient = scanner.nextLine();
                Optional<Client> optionalClient = clientService.getClientByName(nomClient);

                if (optionalClient.isPresent()) {
                    client = optionalClient.get();
                    System.out.println("\n=== Client trouvé : ===");
                    System.out.println("Id : " + client.getId());
                    System.out.println("Nom : " + client.getNom());
                    System.out.println("Adresse : " + client.getAdresse());
                    System.out.println("Telephone : " + client.getTelephone());
                    System.out.println("is Professionnel : " + client.isProfessional());
                } else {
                    System.out.println("Client " + nomClient + " n'existe pas. Veuillez ajouter un nouveau client.");
                    client = addNewClient();
                }
            } else if (option.equals("2")) {
                client = addNewClient();
            } else {
                System.out.println("Option invalide. Veuillez recommencer.");
            }
        } while (!option.equals("1") && !option.equals("2"));

        System.out.print("\n Nom du projet : ");
        String nom = scanner.nextLine();

        System.out.print("Surface du projet : ");
        Float surface = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Est ce que vous voulez ajouter une marge beneficiaire à ce projet? (y/n): ");
        String reponse = scanner.nextLine();
        if (reponse.equals("y")) {
            System.out.print("Entrer la marge beneficiaire de ce projet : ");
            marge = scanner.nextFloat();
            scanner.nextLine();
        }else {
            marge = null;
        }

        Projet projet = new Projet();
        projet.setNom(nom);
        projet.setSurface(surface);
        projet.setMargeBeneficiaire(marge);
        projet.setClient(client);

        projetService.addProjet(projet);
        System.out.println("Projet ajouté avec succès.");

    }

    public Client addNewClient() {
        Client client = new Client();
        clientMenu.addClient();
        return client;
    }

}
