package ui;

import models.entities.Materiel;
import models.entities.Projet;
import services.ComposantService;
import services.ProjetService;
import validators.InputValidator;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MaterielUi {
    private Scanner scanner = new Scanner(System.in);
    private ComposantService materielService;
    private ProjetService projetService;
    float coefficientQualite = 0.0f;
    boolean isValid = false;

    public MaterielUi(ComposantService materielService, ProjetService projetService) {
        this.materielService = materielService;
        this.projetService = projetService;
    }

    public void addMateriel(UUID projetId, Projet projet) {
        System.out.println("=== Ajout d'un nouveau matériau ===");

        String nom;
        while (true) {
            System.out.print("Entrez le nom du matériau : ");
            nom = scanner.nextLine();
            if (InputValidator.validateNonEmptyString(nom)) {
                break;
            }
            System.out.println("Le nom ne peut pas être vide. Veuillez réessayer.");
        }

        float tauxTVA;
        while (true) {
            System.out.print("Entrez le taux de TVA (%) : ");
            tauxTVA = scanner.nextFloat();
            scanner.nextLine();
            if (InputValidator.validatePositiveAmount(tauxTVA)) {
                break;
            }
            System.out.println("Le taux de TVA doit être positif. Veuillez réessayer.");
        }

        float coutUnitaire;
        while (true) {
            System.out.print("Entrez le coût unitaire (€) : ");
            coutUnitaire = scanner.nextFloat();
            scanner.nextLine();
            if (InputValidator.validatePositiveAmount(coutUnitaire)) {
                break;
            }
            System.out.println("Le coût unitaire doit être positif. Veuillez réessayer.");
        }

        float quantite;
        while (true) {
            System.out.print("Entrez la quantité : ");
            quantite = scanner.nextFloat();
            scanner.nextLine();
            if (InputValidator.validatePositiveAmount(quantite)) {
                break;
            }
            System.out.println("La quantité doit être positive. Veuillez réessayer.");
        }

        float coutTransport;
        while (true) {
            System.out.print("Entrez le coût de transport (€) : ");
            coutTransport = scanner.nextFloat();
            scanner.nextLine();
            if (InputValidator.validatePositiveAmount(coutTransport)) {
                break;
            }
            System.out.println("Le coût de transport doit être positif. Veuillez réessayer.");
        }

        while (!isValid) {
            System.out.print("Entrez le coefficient de qualité (1.0 = standard, > 1.0 = haute qualité) : ");
            try {
                coefficientQualite = Float.parseFloat(scanner.nextLine());
                if (InputValidator.validateProductivityFactor(coefficientQualite)) {
                    isValid = true;
                } else {
                    System.out.println("Valeur incorrecte. Veuillez entrer 1.0, 1.1 ou 2.0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
            }
        }

        Materiel materiel = new Materiel();
        materiel.setNom(nom);
        materiel.setTauxTVA(tauxTVA);
        materiel.setCoutUnitaire(coutUnitaire);
        materiel.setQuantite(quantite);
        materiel.setCoutTransport(coutTransport);
        materiel.setCoefficientQualite(coefficientQualite);
        materiel.setProjet(projet);

        int isAdded = materielService.addComposant(materiel, projetId);

        if (isAdded > 0) {
            System.out.println("Le matériau a été ajouté avec succès !");
        } else {
            System.out.println("Une erreur est survenue lors de l'ajout du matériau.");
        }
    }

    public void afficherCoutMateriel(UUID projetId, Projet projet) {
        System.out.println("1- Matériaux :");

        double coutTotal = 0;

        List<Materiel> materielsDuProjet = materielService.getComposantsByProjet(projetId);

        if (materielsDuProjet.isEmpty()) {
            System.out.println("Aucun matériau associé à ce projet.");
            return;
        }

        coutTotal = materielService.CalculateCoutTotalComposant(materielsDuProjet, projet);

        for (Materiel materiel : materielsDuProjet) {
            double cout = materiel.calculerCout();
            System.out.printf("- %s : %.2f € (quantité : %.0f, coût unitaire : %.2f €, qualité : %.1f, transport : %.2f €, TVA : %.0f %%)%n",
                    materiel.getNom(),
                    cout,
                    materiel.getQuantite(),
                    materiel.getCoutUnitaire(),
                    materiel.getCoefficientQualite(),
                    materiel.getCoutTransport(),
                    materiel.getTauxTVA()
            );
        }

        System.out.printf("**Coût total des matériaux : %.2f €**%n", coutTotal);

    }
}
