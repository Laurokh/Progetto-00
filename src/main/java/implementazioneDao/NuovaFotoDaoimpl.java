package implementazioneDao;

import DAO.NuovafotoDao;
import com.galleria_fotografica.connections.Connessione;
import com.galleria_fotografica.model.Luogo;

import java.sql.SQLException;
import java.time.LocalDate;

public class NuovaFotoDaoimpl implements NuovafotoDao {

    @Override
    public void nuovafoto(Boolean privata, Integer utente, String dispositivo, LocalDate data, Luogo luogo) {
        String query = "INSERT INTO Foto VALUES (DEFAULT, '" + privata + "','" + utente + "','" + dispositivo + "','" + data + "', '" + luogo.getIdLuogo() + "')";
        try {
            Connessione db = Connessione.getInstanza();
             db.connessione.createStatement().executeQuery(query);
            db.connessione.close();
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

