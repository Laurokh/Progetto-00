package implementazioneDao;

import DAO.GalleriaDao;
import com.galleria_fotografica.connections.Connessione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GalleriaDaoimpl implements GalleriaDao {

    public ResultSet listaLuoghi() {
        String query= "SELECT * FROM Foto";
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


    public ResultSet luoghiPiuImmoratlati() {
        String query= "SELECT * FROM Q3()";
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

    public ResultSet ordinaPerLuogo(String nomeLuogo) {
        String query= "SELECT * FROM Q1(NomeLuogo)";
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


    public ResultSet ordinaPerTema(String nomeTema) {
        String query= "SELECT * FROM Q2(nomeTema)";
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
