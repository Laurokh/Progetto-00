package implementazioneDao;

import DAO.TemaDao;
import com.galleria_fotografica.connections.Connessione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TemaDaoimpl implements TemaDao {

    public ResultSet caricaTemi () {

        String query = "SELECT * FROM Tema";
        try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();
            return rs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
