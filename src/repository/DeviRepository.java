package repository;

import models.entities.Devi;

import java.util.UUID;

public interface DeviRepository {
    UUID addDevi(Devi devi);
}
