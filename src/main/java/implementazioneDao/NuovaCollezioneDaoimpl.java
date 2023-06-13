package implementazioneDao;

import DAO.NuovaCollezioneDAO;
import com.galleria_fotografica.connections.Connessione;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class NuovaCollezioneDaoimpl implements NuovaCollezioneDAO {


    public ResultSet listautenti (){
        String query = "SELECT * FROM Utente";
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


    public ResultSet newPartecipazione (int idutente){
        String query = "insert into Partecipa_a Values ('"+idutente+"',(select idcollezione from collezione order by idcollezione desc limit 1))";


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





    public ResultSet nuovaCollezione(String nome, LocalDate data) {
        String query = "INSERT INTO Collezione VALUES (DEFAULT,'" + nome + "','" + data + "' )";



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