package ui;

import models.entities.Materiel;
import models.entities.Projet;
import services.ComposantService;
import services.ProjetService;

import java.util.Scanner;
import java.util.UUID;

public class MaterielMenu {
    private Scanner scanner = new Scanner(System.in);
    private ComposantService materielService;
    private ProjetService projetService;
    float coefficientQualite = 0.0f;
    boolean isValid = false;

    public MaterielMenu(ComposantService materielService, ProjetService projetService) {
        this.materielService = materielService;
        this.projetService = projetService;
    }

    public void addMateriel(UUID projetId, Projet projet) {
        System.out.println("=== Ajout d'un nouveau matériau ===");

        System.out.print("Entrez le nom du matériau : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le taux de TVA (%) : ");
        float tauxTVA = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Entrez le coût unitaire (€) : ");
        float coutUnitaire = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Entrez la quantité : ");
        float quantite = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Entrez le coût de transport (€) : ");
        float coutTransport = scanner.nextFloat();
        scanner.nextLine();

//        System.out.print("Entrez le coefficient de qualité (1.0 = standard, > 1.0 = haute qualité) : ");
//        float coefficientQualite = Float.parseFloat(scanner.nextLine());


        while (!isValid) {
            System.out.print("Entrez le coefficient de qualité (1.0 = standard, > 1.0 = haute qualité) : ");
            try {
                coefficientQualite = Float.parseFloat(scanner.nextLine());
                if (coefficientQualite == 1.0f || coefficientQualite == 1.1f || coefficientQualite == 2.0f) {
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
}
