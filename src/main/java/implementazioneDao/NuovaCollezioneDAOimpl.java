package implementazioneDao

import DAO.NuovaCollezioneDAO;
import com.galleria_fotografica.connections.Connessione;
import javafx.scene.chart.PieChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class NuovaCollezioneDAOimpl implements NuovaCollezioneDAO {


    public ResultSet listautenti (){
        String query = "SELECT * FROM Utente";
        try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();
            if (rs.next()) {
                return rs;
            } else {
                return null ;
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }



    public ResultSet nuovaCollezione(String nome, LocalDate data) {
        String query = "INSERT INTO Collezione VALUES (DEFAULT,'" + nome + "','" + data + "' )"

        try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}