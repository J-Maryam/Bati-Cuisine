package ui;

import models.entities.MainDOeuvre;
import models.entities.Projet;
import services.interfaces.IComposantService;
import services.interfaces.IProjetService;

import java.util.Scanner;
import java.util.UUID;

public class MainDOeuvreMenu {
    private Scanner scanner = new Scanner(System.in);
    private IComposantService mainDOeuvreService;
    private IProjetService projetService;
    float productiviteOuvrier = 0.0f;
    boolean isValid = false;

    public MainDOeuvreMenu(IComposantService mainDOeuvreService, IProjetService projetService) {
        this.mainDOeuvreService = mainDOeuvreService;
        this.projetService = projetService;
    }

    public void addMainDOeuvre(UUID projetId, Projet projet) {
        System.out.println("=== Ajout d'un nouveau main d'oeuvre ===");

        System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
        String nom = scanner.nextLine();

        System.out.print("Entrez le taux de TVA (%) : ");
        float tauxTVA = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
        float tauxHoraire = scanner.nextFloat();
        scanner.nextLine();

        System.out.print("Entrez le nombre d'heures travaillées (H): ");
        float heuresTravail = scanner.nextFloat();
        scanner.nextLine();

        while (!isValid) {
            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            try {
                productiviteOuvrier = Float.parseFloat(scanner.nextLine());
                if (productiviteOuvrier == 1.0f || productiviteOuvrier == 1.1f || productiviteOuvrier == 2.0f) {
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
}
