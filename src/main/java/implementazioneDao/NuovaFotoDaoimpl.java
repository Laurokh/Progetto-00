package implementazioneDao;

import DAO.NuovafotoDao;
import com.galleria_fotografica.connections.Connessione;
import com.galleria_fotografica.model.Luogo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class NuovaFotoDaoimpl implements NuovafotoDao {

    @Override
    public ResultSet nuovafoto(String nome, Boolean privata, Integer utente, String dispositivo, LocalDate data, Luogo luogo) {
        String query = "INSERT INTO Foto VALUES ('"+nome+"',DEFAULT, '" + privata + "','" + utente + "','" + dispositivo + "','" + data + "', '" + luogo.getIdLuogo() + "')";
        try {
            Connessione db = Connessione.getInstanza();
            Statement rss = db.connessione.createStatement();
            rss.executeUpdate(query);
            ResultSet rs= rss.getResultSet();
            db.connessione.close();
            return rs ;


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

