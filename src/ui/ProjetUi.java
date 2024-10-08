package ui;

import models.entities.Client;
import models.entities.Projet;
import services.ClientService;
import services.ComposantService;
import services.ProjetService;
import validators.InputValidator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class ProjetUi {
    private Scanner scanner = new Scanner(System.in);
    private ProjetService projetService;
    private ClientService clientService;
    private ClientUi clientUi;
    private MaterielUi materielUi;
    private MainDOeuvreUi mainDOeuvreUi;
    private ComposantService materielService;
    private ComposantService mainDOeuvreService;
    private DeviUi deviUi;

    float remise = 0;

    public ProjetUi(ProjetService projetService, ClientService clientService, ClientUi clientMenu, MaterielUi materielMenu, MainDOeuvreUi mainDOeuvreMenu, ComposantService materielService, ComposantService mainDOeuvreService, DeviUi deviMenu) {
        this.projetService = projetService;
        this.clientService = clientService;
        this.clientUi = clientMenu;
        this.materielUi = materielMenu;
        this.mainDOeuvreUi = mainDOeuvreMenu;
        this.materielService = materielService;
        this.mainDOeuvreService = mainDOeuvreService;
        this.deviUi = deviMenu;
    }

    public void addProject() {
        System.out.println("== Ajouter un nouveau projet ==");

        Client client = null;
        String option;
        Float marge = null;

        // Recherche ou ajout de client
        do {
            System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
            System.out.println("1. Chercher un client existant");
            System.out.println("2. Ajouter un nouveau client");
            System.out.print("Choisissez une option : ");
            option = scanner.nextLine();

            if (option.equals("1")) {
                System.out.println("--- Recherche de client existant ---");
                Optional<Client> clientOpt = clientUi.searchClientByName();

                if (clientOpt.isPresent()) {
                    client = clientOpt.get();
                    System.out.println("Client trouvé : " + client.getNom());

                } else {
                    System.out.println("Veuillez en ajouter un nouveau.");
                    UUID clientId = clientUi.addClient();
                    Optional<Client> newClientOpt = clientService.getClientById(clientId.toString());

                    if (newClientOpt.isPresent()) {
                        client = newClientOpt.get();
                        System.out.println("Nouveau client ajouté et récupéré : " + client.getNom());
                    } else {
                        System.out.println("Erreur lors de la récupération du nouveau client.");
                    }
                }
            } else if (option.equals("2")) {
                UUID clientId = clientUi.addClient();
                Optional<Client> clientOpt = clientService.getClientById(clientId.toString());

                if (clientOpt.isPresent()) {
                    client = clientOpt.get();
                } else {
                    System.out.println("Le client n'a pas pu être récupéré après l'ajout.");
                }
            } else {
                System.out.println("Option invalide. Veuillez recommencer.");
            }
        } while (!option.equals("1") && !option.equals("2"));

        String nom;
        do {
            System.out.print("Nom du projet : ");
            nom = scanner.nextLine();
        } while (!InputValidator.validateNonEmptyString(nom));

        Float surface;
        do {
            System.out.print("Surface du projet : ");
            surface = scanner.nextFloat();
            scanner.nextLine();
        } while (!InputValidator.validatePositiveAmount(surface));

        Projet projet = new Projet();
        projet.setNom(nom);
        projet.setSurface(surface);
        projet.setClient(client);

        UUID projetId = projetService.addProjet(projet);

        // Ajout de matériel
        materielUi.addMateriel(projetId, projet);
        String ajouterAutreMateriel;
        do {
            System.out.print("Voulez-vous ajouter un autre matériel ? (y/n) : ");
            ajouterAutreMateriel = scanner.nextLine();

            if (ajouterAutreMateriel.equalsIgnoreCase("y")) {
                materielUi.addMateriel(projetId, projet);
            } else if (!ajouterAutreMateriel.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Réponse invalide. Veuillez répondre par 'y' ou 'n'.");
            }
        } while (!ajouterAutreMateriel.equalsIgnoreCase("n"));

        // Ajout de main d'œuvre
        mainDOeuvreUi.addMainDOeuvre(projetId, projet);
        String ajouterAutreMainDoeuvre;
        do {
            System.out.print("Voulez-vous ajouter une autre main d'œuvre ? (y/n) : ");
            ajouterAutreMainDoeuvre = scanner.nextLine();

            if (ajouterAutreMainDoeuvre.equalsIgnoreCase("y")) {
                mainDOeuvreUi.addMainDOeuvre(projetId, projet);
            } else if (ajouterAutreMainDoeuvre.equalsIgnoreCase("n")) {
                break;
            } else {
                System.out.println("Réponse invalide. Veuillez répondre par 'y' ou 'n'.");
            }
        } while (true);

        // Calcul du coût total
        System.out.println("--- Calcul du coût total ---");

        System.out.print("Souhaitez-vous ajouter une marge bénéficiaire à ce projet ? (y/n) : ");
        String reponse = scanner.nextLine();
        if (reponse.equals("y")) {
            do {
                System.out.print("Entrer la marge bénéficiaire de ce projet : ");
                marge = scanner.nextFloat();
                scanner.nextLine();
            } while (!InputValidator.validatePositiveAmount(marge));

            if (client.isProfessional()) {
                System.out.print("Le client est professionnel. Entrez le pourcentage de remise à appliquer sur la marge (ex : 10 pour 10%) : ");
                remise = scanner.nextFloat() / 100;
                scanner.nextLine();
                marge = marge * (1 - remise);
                System.out.printf("Une remise de %.0f%% a été appliquée sur la marge bénéficiaire.%n", remise * 100);

            }

            projet.setMargeBeneficiaire(marge);
            projetService.updateProjet(projet);
        }

        System.out.println("Calcul du coût en cours...\n");

        // Affichage des résultats
        System.out.println("--- Résultat du Calcul ---\n");

        System.out.printf("Nom du projet : %s\n", projet.getNom());

        if (projet.getClient() != null) {
            System.out.printf("Client : %s\n", projet.getClient().getNom());
            System.out.printf("Adresse du chantier : %s\n", projet.getClient().getAdresse());
        } else {
            System.out.println("Client: Aucun client associé");
        }

        System.out.printf("Surface du projet : %.2f m²\n", projet.getSurface());

        // Coûts
        System.out.println("\n--- Détail des Coûts ---\n");
        materielService.getComposantsByProjet(projet.getId());

        materielUi.afficherCoutMateriel(projet.getId(), projet);

        System.out.println();
        mainDOeuvreService.getComposantsByProjet(projet.getId());

        mainDOeuvreUi.afficherCoutMainDOeuvre(projet.getId(), projet);
        System.out.println();

        float coutTotalAvantMarge = (float) projetService.calculateCoutTotalAvantMarge(projet.getId(), projet);
        System.out.printf("Coût total avant la marge beneficiaire : %.2f €%n", coutTotalAvantMarge);
        System.out.println();

        double margeBeneficiaire = projetService.calculateMargeBeneficiaire(projet.getId(), projet);
        System.out.printf("Marge beneficiaire : %.2f €%n", margeBeneficiaire);
        System.out.println();

        float coutTotalFinal = (float) projetService.calculateCoutTotalFinal(projet.getId(), projet);
        System.out.printf("* Coût total final du projet : %.2f € *%n", coutTotalFinal);
        System.out.println();

        projet.setCoutTotal(coutTotalFinal);
        projetService.updateProjet(projet);

        if (projetId != null) {
            System.out.println("Projet ajouté avec succès.");
        } else {
            System.out.println("Une erreur est survenue lors de l'ajout du projet.");
        }

        deviUi.addDevi(projetId);

        System.out.println("--- Fin du projet ---");
    }

    public void displayAllProjects() {
        List<Projet> projets = projetService.getAllProjet();

        if (projets.isEmpty()) {
            System.out.println("Aucun projet n'a été trouvé.");
            return;
        }

        System.out.println("--- Liste des Projets ---");
        for (Projet projet : projets) {
//            System.out.println("ID: " + projet.getId());
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

    public void calculerCoutProjet() {
        System.out.println("Veuillez entrer le nom du projet dont vous voulez calculer le coût : ");
        String projectName = scanner.nextLine();

        Optional<Projet> projetOptional = projetService.getProjetByName(projectName);
        if (projetOptional.isEmpty()) {
            System.out.println("Projet non trouvé.");
            return;
        }

        Projet projet = projetOptional.get();

        System.out.println("Calcul du coût en cours...\n");

        System.out.println("--- Résultat du Calcul ---\n");

        System.out.printf("Nom du projet : %s", projet.getNom());

        if (projet.getClient() != null) {
            System.out.printf("Client : %s%n\n", projetOptional.get().getClient().getNom());
            System.out.printf("Adresse du chantier : %s%n\n", projetOptional.get().getClient().getAdresse());
        } else {
            System.out.println("Client : Aucun client associé");
        }

        System.out.printf("Surface du projet : %.2f m²\n", projet.getSurface());

        System.out.println("\n--- Détail des Coûts ---\n");
        materielService.getComposantsByProjet(projet.getId());

        materielUi.afficherCoutMateriel(projet.getId(), projet);

        System.out.println();
        mainDOeuvreService.getComposantsByProjet(projet.getId());

        mainDOeuvreUi.afficherCoutMainDOeuvre(projet.getId(), projet);
        System.out.println();

        float coutTotalAvantMarge = (float) projetService.calculateCoutTotalAvantMarge(projet.getId(), projet);
        System.out.printf("Coût total avant la marge beneficiaire : %.2f €%n", coutTotalAvantMarge);
        System.out.println();

        double margeBeneficiaire = projetService.calculateMargeBeneficiaire(projet.getId(), projet);
        System.out.printf("Marge beneficiaire : %.2f €%n", margeBeneficiaire);
        System.out.println();

        float coutTotalFinal = (float) projetService.calculateCoutTotalFinal(projet.getId(), projet);
        System.out.printf("* Coût total final du projet : %.2f € *%n", coutTotalFinal);
        System.out.println();

    }
}
