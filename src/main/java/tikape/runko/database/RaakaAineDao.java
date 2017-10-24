package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tikape.runko.domain.RaakaAine;

public class RaakaAineDao implements Dao<RaakaAine, Integer> {

    private Database database;

    public RaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public RaakaAine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");

        RaakaAine ra = new RaakaAine(id, nimi);

        rs.close();
        stmt.close();
        connection.close();

        return ra;
    }

    @Override
    public List<RaakaAine> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine");

        ResultSet rs = stmt.executeQuery();
        List<RaakaAine> raakaAineet = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            raakaAineet.add(new RaakaAine(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();
        
        return raakaAineet;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM RaakaAine WHERE id = ?");
        stmt.setInt(1, key);
        stmt.execute();

    }

    @Override
    public RaakaAine saveOrUpdate(RaakaAine object) throws SQLException {
        // tallenetaan vain jos saman nimisiä ei ole tallennettu jo

        RaakaAine byName = findByName(object.getNimi());

        if (byName != null) {
            return byName;
        }

        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO RaakaAine (nimi) VALUES (?)");
            stmt.setString(1, object.getNimi());
            stmt.executeUpdate();
        }

        return findByName(object.getNimi());

    }

    private RaakaAine findByName(String name) throws SQLException {
        try (Connection conn = database.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM RaakaAine WHERE nimi = ?");
            stmt.setString(1, name);

            ResultSet result = stmt.executeQuery();
            if (!result.next()) {
                return null;
            }

            return new RaakaAine(result.getInt(1), result.getString(2));
        }
    }

    public List<String> findAllById(Integer annos_id) throws SQLException {
        List<String> aineJaOhje = new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM RaakaAine, AnnosRaakaAine "
                + "WHERE AnnosRaakaAine.annos_id = ? "
                + "AND AnnosRaakaAine.raaka_aine_id = RaakaAine.id");
        stmt.setInt(1, annos_id);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            
            String rivi = rs.getString("nimi");
            
            if (!rs.getString("jarjestys").isEmpty()) {
                rivi +=  ", " + (rs.getString("jarjestys"));
            }

            if (!rs.getString("maara").isEmpty()) {
                rivi += ", " + (rs.getString("maara"));
            }

            if (!rs.getString("ohje").isEmpty()) {
                rivi += ", " + (rs.getString("ohje"));
            }

            aineJaOhje.add(rivi);
        }

        rs.close();
        stmt.close();
        connection.close();

        return aineJaOhje;
    }

}
