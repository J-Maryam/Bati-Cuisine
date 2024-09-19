package dao.interfaces;

import models.entities.Composant;

import java.util.List;
import java.util.Optional;

public interface IComposantDao {
    int addComposant(Composant composant);
    int updateComposant(Composant composant);
    int deleteComposant(int id);
    Optional<Composant> getComposantById(int id);
    List<Composant> getComposants();
}
