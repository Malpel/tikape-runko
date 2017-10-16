package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.AnnosRaakaAine;

public class AnnosRaakaAineDao implements Dao<AnnosRaakaAine, Integer> {

    private Database database;

    public AnnosRaakaAineDao(Database database) {
        this.database = database;
    }

    @Override
    public AnnosRaakaAine findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM AnnosRaakaAine WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer raakaAineId = rs.getInt("raaka_aine_id");
        Integer annosId = rs.getInt("annos_id");
        Integer jarjestys = rs.getInt("jarjestys");
        Integer maara = rs.getInt("maara");
        String ohje = rs.getString("ohje");

        AnnosRaakaAine ara = new AnnosRaakaAine(annosId, raakaAineId, jarjestys, maara, ohje);

        rs.close();
        stmt.close();
        connection.close();

        return ara;
    }

    @Override
    public List<AnnosRaakaAine> findAll() throws SQLException {

        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Annos");

        ResultSet rs = stmt.executeQuery();
        List<AnnosRaakaAine> arat = new ArrayList<>();
        while (rs.next()) {

            Integer raakaAineId = rs.getInt("raaka_aine_id");
            Integer annosId = rs.getInt("annos_id");
            Integer jarjestys = rs.getInt("jarjestys");
            Integer maara = rs.getInt("maara");
            String ohje = rs.getString("ohje");

            arat.add(new AnnosRaakaAine(annosId, raakaAineId, jarjestys, maara, ohje));
        }

        rs.close();
        stmt.close();
        connection.close();

        return arat;
    }

    @Override
    public AnnosRaakaAine saveOrUpdate(AnnosRaakaAine ara) throws SQLException{
        return ara;
    }

    @Override
    public void delete(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Annos WHERE id = ?");
        stmt.setInt(1, key);
        stmt.execute();

    }

    

}
