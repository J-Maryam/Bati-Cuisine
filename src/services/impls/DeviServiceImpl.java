package services.impls;

import models.entities.Devi;
import repository.DeviRepository;
import services.DeviService;

import java.util.UUID;

public class DeviServiceImpl implements DeviService {
    private DeviRepository deviRepository;
    public DeviServiceImpl(DeviRepository deviRepository) {
        this.deviRepository = deviRepository;
    }
    @Override
    public UUID addDevi(Devi devi) {
        return deviRepository.addDevi(devi);
    }
}
