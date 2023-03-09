package implementazioneDao;

import DAO.NuovafotoDao;
import com.galleria_fotografica.connections.Connessione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class NuovaFotoDaoimpl implements NuovafotoDao {
    public ResultSet nuovafoto(Boolean privata, Integer utente, String dispositivo, LocalDate data, Integer luogo) {
        String query = "INSERT INTO Foto VALUES (DEFAULT, '" + privata + "','" + utente + "','" + dispositivo + "','" + data + "', '" + luogo + "')";
        try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();
            if (rs.next()) {
                return rs;
            } else {
                return null;
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

