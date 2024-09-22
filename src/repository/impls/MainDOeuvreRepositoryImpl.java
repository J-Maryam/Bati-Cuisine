package repository.impls;

import models.entities.Materiel;
import repository.ComposantRepository;
import models.entities.MainDOeuvre;
import models.enums.TypeComposant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MainDOeuvreRepositoryImpl implements ComposantRepository<MainDOeuvre> {

    private Connection connection;
    public MainDOeuvreRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int addComposant(MainDOeuvre mainDOeuvre, UUID projetId) {
        mainDOeuvre.setTypeComposant(TypeComposant.MAINDOEUVRE);
        String sql = "INSERT INTO mainDOeuvres (nom, tauxTVA, typeComposant, projetId, tauxHoraire, heuresTravail, productiviteOuvrier) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, mainDOeuvre.getNom());
            ps.setFloat(2, mainDOeuvre.getTauxTVA());
            ps.setObject(3, mainDOeuvre.getTypeComposant().name(), java.sql.Types.OTHER);
            ps.setObject(4, projetId);
            ps.setFloat(5, mainDOeuvre.getTauxHoraire());
            ps.setFloat(6, mainDOeuvre.getHeuresTravail());
            ps.setFloat(7, mainDOeuvre.getProductiviteOuvrier());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<MainDOeuvre> getComposantsByProjet(UUID projetId) {
        List<MainDOeuvre> mainDOeuvres = new ArrayList<>();
        String query = "SELECT * FROM mainDOeuvres WHERE projetId = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setObject(1, projetId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MainDOeuvre mainDOeuvre = new MainDOeuvre();
                mainDOeuvre.setId((UUID) rs.getObject("id"));
                mainDOeuvre.setNom(rs.getString("nom"));
                mainDOeuvre.setTauxTVA(rs.getFloat("tauxTVA"));
                mainDOeuvre.setTauxHoraire(rs.getFloat("tauxHoraire"));
                mainDOeuvre.setHeuresTravail(rs.getFloat("heuresTravail"));
                mainDOeuvre.setProductiviteOuvrier(rs.getFloat("productiviteOuvrier"));

                mainDOeuvres.add(mainDOeuvre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mainDOeuvres;    }


}
