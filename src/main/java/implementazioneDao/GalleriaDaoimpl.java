package implementazioneDao;

import DAO.GalleriaDao;
import com.galleria_fotografica.connections.Connessione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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
        String query= "SELECT L.Nome, COUNT(F.IDFoto) as Immortalazioni\n" +
                "                 FROM Luogo AS L\n" +
                "                 LEFT JOIN Foto AS F ON L.IDLuogo = F.IDLuogo\n" +
                "                 GROUP BY L.Nome\n" +
                "                 ORDER BY Immortalazioni DESC\n" +
                "                 LIMIT 3;";
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

    public ResultSet ordinaPerLuogo(String nomeLuogo,int id ) {
        String query= "SELECT * FROM Q1('"+nomeLuogo+"') as l where l.idutente = '"+id+"'";
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

    public ResultSet ordinaPerCollezione (int idCollezione){
        String query= "SELECT f.*\n" +
                "FROM Foto f\n" +
                "INNER JOIN compone a ON f.idfoto = a.idfoto\n" +
                "WHERE a.idCollezione = '"+idCollezione+"'";
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

    public ResultSet ordinaPerTema(String nomeTema ,int id) {
        String query= "SELECT * FROM Q2('"+nomeTema+"') as c where c.idUtente= '"+id+"'";
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

    public void eliminaFoto (String id){
        String query= "DELETE FROM FOTO where idfoto='"+id+"'";
        try {
            Connessione db = Connessione.getInstanza();
            Statement rss = db.connessione.createStatement();
            rss.executeUpdate(query);
            db.connessione.close();


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void isPrivate (String id) {
        String query= "Update FOTO Set privata = 'true' where idFoto = '"+id+"'";
        try {
            Connessione db = Connessione.getInstanza();
            Statement rss = db.connessione.createStatement();
            rss.executeUpdate(query);
            rss.getResultSet();
            db.connessione.close();


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public void isPubblica (String id) {
            String query= "Update FOTO Set privata = 'false' where idFoto = '"+id+"'";
            try {
                Connessione db = Connessione.getInstanza();
                Statement rss = db.connessione.createStatement();
                rss.executeUpdate(query);
                rss.getResultSet();
                db.connessione.close();


            } catch (
                    SQLException e) {
                throw new RuntimeException(e);
            }

    }

    public void aggiungiaCollezione (int idCollezione, int idFoto){
        String query= "INSERT INTO Compone VALUES('"+idFoto+"','"+idCollezione+"','"+LocalDate.now()+"');";
        try {
            Connessione db = Connessione.getInstanza();
            Statement rss = db.connessione.createStatement();
            rss.executeUpdate(query);
            rss.getResultSet();
            db.connessione.close();


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public ResultSet listaCompone(){
        String query= "SELECT * FROM Compone";
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

    public void eliminaCollezione(String elimina) {
        String query= "DELETE FROM Collezione where nome='"+elimina+"'";
        try {
            Connessione db = Connessione.getInstanza();
            Statement rss = db.connessione.createStatement();
            rss.executeUpdate(query);
            rss.getResultSet();
            db.connessione.close();


        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}








