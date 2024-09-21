package ui;

import models.entities.Client;
import models.entities.Projet;
import services.ClientService;
import services.ProjetService;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class ProjetMenu {
    private Scanner scanner = new Scanner(System.in);
    private ProjetService projetService;
    private ClientService clientService;
    private ClientMenu clientMenu;
    private MaterielMenu materielMenu;
    private MainDOeuvreMenu mainDOeuvreMenu;

    public ProjetMenu(ProjetService projetService, ClientService clientService, ClientMenu clientMenu, MaterielMenu materielMenu, MainDOeuvreMenu mainDOeuvreMenu) {
        this.projetService = projetService;
        this.clientService = clientService;
        this.clientMenu = clientMenu;
        this.materielMenu = materielMenu;
        this.mainDOeuvreMenu = mainDOeuvreMenu;
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
                    System.out.println("Téléphone : " + client.getTelephone());
                    System.out.println("Est professionnel : " + client.isProfessional());
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

        System.out.print("\nNom du projet : ");
        String nom = scanner.nextLine();

        System.out.print("Surface du projet : ");
        Float surface = scanner.nextFloat();
        scanner.nextLine();

        Projet projet = new Projet();
        projet.setNom(nom);
        projet.setSurface(surface);
        projet.setClient(client);

        UUID projetId = projetService.addProjet(projet);

        materielMenu.addMateriel(projetId, projet);

        mainDOeuvreMenu.addMainDOeuvre(projetId, projet);

        System.out.print("Voulez-vous ajouter une marge bénéficiaire à ce projet ? (y/n) : ");
        String reponse = scanner.nextLine();
        if (reponse.equals("y")) {
            System.out.print("Entrer la marge bénéficiaire de ce projet : ");
            marge = scanner.nextFloat();
            scanner.nextLine();
            projet.setMargeBeneficiaire(marge);
            projetService.updateProjet(projet);
        }

        if (projetId != null) {
            System.out.println("Projet ajouté avec succès.");
        } else {
            System.out.println("Une erreur est survenue lors de l'ajout du projet.");
        }
    }

    public Client addNewClient() {
        UUID clientId = clientMenu.addClient();
        Client client = new Client();
        client.setId(clientId);
        return client;
    }

}
