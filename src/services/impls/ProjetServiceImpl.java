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
    public int updateProjet(Projet projet) {
        return projetRepository.updateProjet(projet);
    }

    @Override
    public Optional<Projet> getProjetById(UUID projetId) {
        return projetRepository.getProjetById(projetId);
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
        return coutTotalAvantMarge * (marge / 100);
    }

    @Override
    public double calculateCoutTotalFinal(UUID projetId, Projet projet) {
        double coutTotalAvantMarge = calculateCoutTotalAvantMarge(projetId, projet);
        double margeBeneficiaire = calculateMargeBeneficiaire(projetId, projet);
        return coutTotalAvantMarge + margeBeneficiaire;
    }
}
