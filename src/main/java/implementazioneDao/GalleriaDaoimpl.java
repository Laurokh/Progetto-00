package implementazioneDao;

import DAO.GalleriaDao;
import com.galleria_fotografica.connections.Connessione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GalleriaDaoimpl implements GalleriaDao {

    public ResultSet listaFoto(int uId){
        String query= "SELECT * FROM foto where idutente = '"+uId+"'";
        try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();

                return rs;


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet listaLuoghi() {
        String query= "SELECT * FROM luogo";
        try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();
            return rs;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ResultSet listaTemi() {
        String query= "SELECT * FROM tema";
        try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();
            return rs;


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
                return rs;
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet ordinaPerLuogo(String nomeLuogo) {
        String query= "SELECT * FROM Q1('"+nomeLuogo+"')";
        try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();

                return rs;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet listaCollezioni (int id){
        String query= "select * from collezione as c natural join partecipa_a where partecipa_a.idutente = '"+id+"'";
        try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();

            return rs;

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public ResultSet ordinaPerTema(String nomeTema) {
        String query= "SELECT * FROM Q2('"+nomeTema+"')";
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
