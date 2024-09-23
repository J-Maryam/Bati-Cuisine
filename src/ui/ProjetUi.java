package ui;

import models.entities.Client;
import models.entities.Projet;
import services.ClientService;
import services.ComposantService;
import services.ProjetService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class ProjetUi {
    private Scanner scanner = new Scanner(System.in);
    private ProjetService projetService;
    private ClientService clientService;
    private ClientUi clientMenu;
    private MaterielUi materielMenu;
    private MainDOeuvreUi mainDOeuvreMenu;
    private ComposantService materielService;
    private ComposantService mainDOeuvreService;
    private DeviUi deviMenu;

    public ProjetUi(ProjetService projetService, ClientService clientService, ClientUi clientMenu, MaterielUi materielMenu, MainDOeuvreUi mainDOeuvreMenu, ComposantService materielService, ComposantService mainDOeuvreService, DeviUi deviMenu) {
        this.projetService = projetService;
        this.clientService = clientService;
        this.clientMenu = clientMenu;
        this.materielMenu = materielMenu;
        this.mainDOeuvreMenu = mainDOeuvreMenu;
        this.materielService = materielService;
        this.mainDOeuvreService = mainDOeuvreService;
        this.deviMenu = deviMenu;
    }

    public void PrincipleMenu() {
        boolean running = true;

        int choice;
        while (running) {
            System.out.println("==== Menu Principale ====");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("0. Quitter");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProject();
                    break;
                case 2:
                    displayAllProjects();
                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Exiting ...");
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
        String ajouterAutreMateriel;
        do {
            System.out.print("Voulez-vous ajouter un autre matériel ? (y/n) : ");
            ajouterAutreMateriel = scanner.nextLine();

            if (ajouterAutreMateriel.equalsIgnoreCase("y")) {
                materielMenu.addMateriel(projetId, projet);
            } else if (!ajouterAutreMateriel.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Réponse invalide. Veuillez répondre par 'y' ou 'n'.");
            }
        } while (!ajouterAutreMateriel.equalsIgnoreCase("n"));


        mainDOeuvreMenu.addMainDOeuvre(projetId, projet);
        String ajouterAutreMainDoeuvre;
        do {
            System.out.print("Voulez-vous ajouter une autre main d'œuvre ? (y/n) : ");
            ajouterAutreMainDoeuvre = scanner.nextLine();

            if (ajouterAutreMainDoeuvre.equalsIgnoreCase("y")) {
                mainDOeuvreMenu.addMainDOeuvre(projetId, projet);
            } else if (ajouterAutreMainDoeuvre.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Réponse invalide. Veuillez répondre par 'y' ou 'n'.");
            }
        } while (true);

        System.out.println("--- Calcul du coût total ---");

        System.out.print("Souhaitez-vous ajouter une marge bénéficiaire à ce projet ? (y/n) : ");
        String reponse = scanner.nextLine();
        if (reponse.equals("y")) {
            System.out.print("Entrer la marge bénéficiaire de ce projet : ");
            marge = scanner.nextFloat();
            scanner.nextLine();
            projet.setMargeBeneficiaire(marge);
            projetService.updateProjet(projet);
        }

        System.out.println("Calcul du coût en cours...\n");

        System.out.println("--- Résultat du Calcul ---\n");

        System.out.printf("Nom du projet : %s", projet.getNom());
        System.out.printf("\nClient : %s", projet.getClient().getNom());
        System.out.printf("\nAdresse du chantier : %s", projet.getClient().getAdresse());
        System.out.printf("\nSurface du projet : %.2f m²", projet.getSurface());

        System.out.println("\n--- Détail des Coûts ---");
        materielService.getComposantsByProjet(projet.getId());

        materielMenu.afficherCoutMateriel(projetId, projet);

        mainDOeuvreService.getComposantsByProjet(projet.getId());

        mainDOeuvreMenu.afficherCoutMainDOeuvre(projetId, projet);

        double coutTotalAvantMarge = projetService.calculateCoutTotalAvantMarge(projetId, projet);
        System.out.printf("Coût total avant la marge beneficiaire : %.2f €%n", coutTotalAvantMarge);

        double margeBeneficiaire = projetService.calculateMargeBeneficiaire(projetId, projet);
        System.out.printf("Marge beneficiaire : %.2f €%n", margeBeneficiaire);

        float coutTotalFinal = (float) projetService.calculateCoutTotalFinal(projetId, projet);
        System.out.printf("* Coût total final du projet : %.2f € *%n", coutTotalFinal);

        projet.setCoutTotal(coutTotalFinal);
        projetService.updateProjet(projet);

        deviMenu.addDevi(projetId);

        if (projetId != null) {
            System.out.println("Projet ajouté avec succès.");
        } else {
            System.out.println("Une erreur est survenue lors de l'ajout du projet.");
        }

        System.out.println("--- Fin du projet ---");
    }

    public Client addNewClient() {
        UUID clientId = clientMenu.addClient();
        Client client = new Client();
        client.setId(clientId);
        return client;
    }

    public void displayAllProjects() {
        List<Projet> projets = projetService.getAllProjet();

        if (projets.isEmpty()) {
            System.out.println("Aucun projet n'a été trouvé.");
            return;
        }

        System.out.println("--- Liste des Projets ---");
        for (Projet projet : projets) {
            System.out.println("ID: " + projet.getId());
            System.out.println("Nom: " + projet.getNom());
            System.out.println("Surface: " + projet.getSurface() + " m²");
            System.out.println("Marge Bénéficiaire: " + projet.getMargeBeneficiaire() + " %");
            System.out.println("Coût Total: " + projet.getCoutTotal() + " €");
            System.out.println("État du Projet: " + projet.getEtatProjet());
            if (projet.getClient() != null) {
                System.out.println("Client: " + projet.getClient().getNom());
            } else {
                System.out.println("Client: Aucun client associé");
            }
            System.out.println("--------------------------");
        }
    }


}
