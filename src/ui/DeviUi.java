package ui;

import models.entities.Devi;
import models.entities.Projet;
import services.DeviService;
import services.ProjetService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class DeviUi {
    private Scanner scanner = new Scanner(System.in);
    private DeviService deviService;
    private ProjetService projetService;

    public DeviUi(DeviService deviService, ProjetService projetService) {
        this.deviService = deviService;
        this.projetService = projetService;
    }

    public void addDevi(UUID projetId) {
        System.out.println("--- Enregistrement du Devis ---");

        System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
        String dateEmissionStr = scanner.nextLine();
        LocalDate dateEmission = LocalDate.parse(dateEmissionStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
        String dateValiditeStr = scanner.nextLine();
        LocalDate dateValidite = LocalDate.parse(dateValiditeStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        float montantEstime = (float) projetService.calculateCoutTotalFinal(projetId, new Projet());

        Optional<Projet> projet = projetService.getProjetById(projetId);

        if (projet.isEmpty()) {
            System.out.println("Projet non trouvé. Impossible de créer le devis.");
            return;
        }

        Devi devi = new Devi();
        devi.setDateEmission(dateEmission);
        devi.setDateValidite(dateValidite);
        devi.setMontantEstime(montantEstime);
        devi.setProjet(projet.get());

        while (true){
            System.out.print("Souhaitez-vous enregistrer le devis ? (y/n) : ");
            String reponse = scanner.nextLine();

            if (reponse.equalsIgnoreCase("y")) {
                devi.setAccepte(true);
                UUID deviId = deviService.addDevi(devi);
                if (deviId != null) {
                    System.out.println("Devis enregistré avec succès !");
                } else {
                    System.out.println("Erreur lors de l'enregistrement du devis.");
                }
                break;
            } else if (reponse.equalsIgnoreCase("n")) {
                devi.setAccepte(false);
                deviService.addDevi(devi);
                break;
            }else{
                System.out.println("Réponse invalide. Veuillez répondre par 'y' ou 'n'.");
            }
        }
    }

}
