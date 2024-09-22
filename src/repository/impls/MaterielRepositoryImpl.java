package repository.impls;

import repository.ComposantRepository;
import models.entities.Materiel;
import models.enums.TypeComposant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MaterielRepositoryImpl implements ComposantRepository<Materiel> {

    private Connection connection;
    public MaterielRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addComposant(Materiel materiel, UUID projetId) {
        materiel.setTypeComposant(TypeComposant.MATERIEL);
        String sql = "INSERT INTO materiaux (nom, tauxTVA, typeComposant, projetId, coutUnitaire, quantite, coutTransport, coefficientQualite) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, materiel.getNom());
            ps.setFloat(2, materiel.getTauxTVA());
            ps.setObject(3, materiel.getTypeComposant().name(), java.sql.Types.OTHER);
            ps.setObject(4, projetId);
            ps.setFloat(5, materiel.getCoutUnitaire());
            ps.setFloat(6, materiel.getQuantite());
            ps.setFloat(7, materiel.getCoutTransport());
            ps.setFloat(8, materiel.getCoefficientQualite());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Materiel> getComposantsByProjet(UUID projetId) {
        List<Materiel> materiels = new ArrayList<>();
        String query = "SELECT * FROM materiaux WHERE projetId = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, projetId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Materiel materiel = new Materiel();
                materiel.setId((UUID) rs.getObject("id"));
                materiel.setNom(rs.getString("nom"));
                materiel.setTauxTVA(rs.getFloat("tauxTVA"));
                materiel.setCoutUnitaire(rs.getFloat("coutUnitaire"));
                materiel.setQuantite(rs.getFloat("quantite"));
                materiel.setCoutTransport(rs.getFloat("coutTransport"));
                materiel.setCoefficientQualite(rs.getFloat("coefficientQualite"));

                materiels.add(materiel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiels;
    }


}
