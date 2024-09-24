package ui;

import models.entities.MainDOeuvre;
import models.entities.Projet;
import services.ComposantService;
import services.ProjetService;
import validators.InputValidator;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MainDOeuvreUi {
    private Scanner scanner = new Scanner(System.in);
    private ComposantService mainDOeuvreService;
    private ProjetService projetService;
    float productiviteOuvrier = 0.0f;
    boolean isValid = false;

    public MainDOeuvreUi(ComposantService mainDOeuvreService, ProjetService projetService) {
        this.mainDOeuvreService = mainDOeuvreService;
        this.projetService = projetService;
    }

    public void addMainDOeuvre(UUID projetId, Projet projet) {
        System.out.println("=== Ajout d'un nouveau main d'oeuvre ===");

        String nom;
        while (true) {
            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
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

        float tauxHoraire;
        while (true) {
            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            tauxHoraire = scanner.nextFloat();
            scanner.nextLine();
            if (InputValidator.validatePositiveAmount(tauxHoraire)) {
                break;
            }
            System.out.println("Le taux horaire doit être positif. Veuillez réessayer.");
        }

        float heuresTravail;
        while (true) {
            System.out.print("Entrez le nombre d'heures travaillées (H): ");
            heuresTravail = scanner.nextFloat();
            scanner.nextLine();
            if (InputValidator.validatePositiveAmount(heuresTravail)) {
                break;
            }
            System.out.println("Le nombre d'heures travaillées doit être positif. Veuillez réessayer.");
        }

        while (!isValid) {
            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            try {
                productiviteOuvrier = Float.parseFloat(scanner.nextLine());
                if (InputValidator.validateProductivityFactor(productiviteOuvrier)) {
                    isValid = true;
                } else {
                    System.out.println("Valeur incorrecte. Veuillez entrer 1.0, 1.1 ou 2.0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
            }
        }


        MainDOeuvre mainDOeuvre = new MainDOeuvre();
        mainDOeuvre.setNom(nom);
        mainDOeuvre.setTauxTVA(tauxTVA);
        mainDOeuvre.setTauxHoraire(tauxHoraire);
        mainDOeuvre.setHeuresTravail(heuresTravail);
        mainDOeuvre.setProductiviteOuvrier(productiviteOuvrier);
        mainDOeuvre.setProjet(projet);

        int isAdded = mainDOeuvreService.addComposant(mainDOeuvre, projetId);

        if (isAdded > 0) {
            System.out.println("La main-d'œuvre a été ajoutée avec succès !");
        } else {
            System.out.println("Une erreur est survenue lors de l'ajout du main-d'œuvre.");
        }
    }

    public void afficherCoutMainDOeuvre(UUID projetId, Projet projet) {
        System.out.println("2- Mains d'oeuvres :");

        double coutTotal = 0;

        List<MainDOeuvre> mainDOeuvresDuProjet = mainDOeuvreService.getComposantsByProjet(projetId);

        if (mainDOeuvresDuProjet.isEmpty()) {
            System.out.println("Aucun main d'oeuvre associé à ce projet.");
            return;
        }

        coutTotal = mainDOeuvreService.CalculateCoutTotalComposant(mainDOeuvresDuProjet, projet);

        for (MainDOeuvre mainDOeuvre : mainDOeuvresDuProjet) {
            double cout = mainDOeuvre.calculerCout();
            System.out.printf("- %s : %.2f € (taux horaire : %.2f €/H, heures de travail : %.0f H, productivité des ouvriers : %.0f, TVA : %.0f %%)%n",
                    mainDOeuvre.getNom(),
                    cout,
                    mainDOeuvre.getTauxHoraire(),
                    mainDOeuvre.getHeuresTravail(),
                    mainDOeuvre.getProductiviteOuvrier(),
                    mainDOeuvre.getTauxTVA()
            );
        }

        System.out.printf("** Coût total des mains d'oeuvre : %.2f € **%n", coutTotal);
   }

}
