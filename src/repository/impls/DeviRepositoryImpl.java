package repository.impls;

import models.entities.Devi;
import repository.DeviRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class DeviRepositoryImpl implements DeviRepository {

    private final Connection connection;

    public DeviRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UUID addDevi(Devi devi) {
        String sql = "INSERT INTO devis (montantEstime, dateEmission, dateValidite, accepte, projetId) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            UUID deviId = UUID.randomUUID();

            ps.setFloat(1, devi.getMontantEstime());
            ps.setObject(2, devi.getDateEmission());
            ps.setObject(3, devi.getDateValidite());
            ps.setBoolean(4, devi.isAccepte());
            ps.setObject(5, devi.getProjet().getId());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                return deviId;
            } else {
                throw new SQLException("Erreur lors de l'insertion du devis.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
