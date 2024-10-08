package services.impls;

import models.entities.MainDOeuvre;
import models.entities.Materiel;
import repository.ProjetRepository;
import models.entities.Projet;
import services.ComposantService;
import services.ProjetService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjetServiceImpl implements ProjetService {
    private ProjetRepository projetRepository;
    private ComposantService materielService;
    private ComposantService mainDOeuvreService
            ;
    public ProjetServiceImpl(ProjetRepository projetRepository, ComposantService materielService, ComposantService mainDOeuvreService) {
        this.projetRepository = projetRepository;
        this.materielService = materielService;
        this.mainDOeuvreService = mainDOeuvreService;
    }

    @Override
    public UUID addProjet(Projet projet) {
        return projetRepository.addProjet(projet);
    }

    @Override
    public UUID updateProjet(Projet projet) {
        return projetRepository.updateProjet(projet);
    }

    @Override
    public Optional<Projet> getProjetById(UUID projetId) {
        return projetRepository.getProjetById(projetId);
    }

    @Override
    public Optional<Projet> getProjetByName(String projectName) {
        return projetRepository.getProjetByName(projectName);
    }

    @Override
    public List<Projet> getAllProjet() {
        return projetRepository.getAllProjet();
    }

    @Override
    public double calculateCoutTotalAvantMarge(UUID projetId, Projet projet) {
        List<Materiel> materiels = materielService.getComposantsByProjet(projetId);
        List<MainDOeuvre> mainDOeuvres = mainDOeuvreService.getComposantsByProjet(projetId);

        float coutTotalMateriaux = (float) materielService.CalculateCoutTotalComposant(materiels, projet);
        float coutTotalMainDOeuvres = (float) mainDOeuvreService.CalculateCoutTotalComposant(mainDOeuvres, projet);

        float coutTotalAvantMarge = coutTotalMateriaux + coutTotalMainDOeuvres;
        return coutTotalAvantMarge;
    }

    @Override
    public double calculateMargeBeneficiaire(UUID projetId, Projet projet) {
        double coutTotalAvantMarge = calculateCoutTotalAvantMarge(projetId, projet);
        double marge = projet.getMargeBeneficiaire();
        double margeBeneficiaire = coutTotalAvantMarge * (marge / 100);
        return margeBeneficiaire;
    }

    @Override
    public double calculateCoutTotalFinal(UUID projetId, Projet projet) {
        double coutTotalAvantMarge = calculateCoutTotalAvantMarge(projetId, projet);
        double margeBeneficiaire = calculateMargeBeneficiaire(projetId, projet);
        return coutTotalAvantMarge + margeBeneficiaire;
    }
}
