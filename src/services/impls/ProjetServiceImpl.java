package services.impls;

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
    public int deleteProjet(UUID id) {
        return 0;
    }

    @Override
    public Optional<Projet> getProjetById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<Projet> getProjetByName(String projectName) {
        return Optional.empty();
    }

    @Override
    public List<Projet> getAllProjet() {
        return List.of();
    }

    @Override
    public double calculateCoutTotal(Projet projet) {
        float coutMateriaux = (float) materielService.CalculateCoutTotalComposant(projet.getComposants(), projet);
        float coutMainDOeuvre = (float) mainDOeuvreService.CalculateCoutTotalComposant(projet.getComposants(), projet);

        float coutTotalAvantMarge = coutMateriaux + coutMainDOeuvre;
        float marge = coutTotalAvantMarge * (projet.getMargeBeneficiaire() / 100);
        float coutTotalFinal = marge + coutTotalAvantMarge;

        projet.setCoutTotal(coutTotalFinal);
        return coutTotalFinal;
    }
}
