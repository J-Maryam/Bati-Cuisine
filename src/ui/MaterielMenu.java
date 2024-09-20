package ui;

import models.entities.Materiel;
import models.entities.Projet;
import services.interfaces.IComposantService;
import services.interfaces.IProjetService;

import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class MaterielMenu {
    private Scanner scanner = new Scanner(System.in);
    private IComposantService materielService;
    private IProjetService projetService;

    public MaterielMenu(IComposantService materielService, IProjetService projetService) {
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

        System.out.print("Entrez le coefficient de qualité (1.0 = standard, > 1.0 = haute qualité) : ");
        float coefficientQualite = scanner.nextFloat();
        scanner.nextLine();

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
