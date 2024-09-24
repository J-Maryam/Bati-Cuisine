package ui;

import services.ClientService;
import services.ComposantService;
import services.ProjetService;

import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);
    private ClientUi clientUi;
    private ProjetUi projetUi;

    public MainMenu(ClientUi clientUi, ProjetUi projetUi) {
        this.clientUi = clientUi;
        this.projetUi = projetUi;
    }

    public void PrincipleMenu() {
        boolean running = true;

        int choice;
        while (running) {
            System.out.println("==== Menu Principale ====");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Ajouter un nouveau client");
            System.out.println("5. afficher les clients existants");
            System.out.println("0. Quitter");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    projetUi.addProject();
                    break;
                case 2:
                    projetUi.displayAllProjects();
                    break;
                case 3:
                    projetUi.calculerCoutProjet();
                    break;
                case 4:
                    clientUi.addClient();
                    break;
                case 5:
                    clientUi.getAllClient();
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
}
