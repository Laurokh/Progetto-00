package implementazioneDao;

import DAO.LuogoDao;
import com.galleria_fotografica.connections.Connessione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LuogoDaoimpl implements LuogoDao {
    public ResultSet caricaLuogo() {

        String query = "SELECT * FROM luogo";
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
