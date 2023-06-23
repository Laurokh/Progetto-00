package implementazioneDao;

import DAO.NuovafotoDao;
import com.galleria_fotografica.connections.Connessione;
import com.galleria_fotografica.model.Luogo;

import java.sql.*;
import java.time.LocalDate;

public class NuovaFotoDaoimpl implements NuovafotoDao {

    @Override
    public ResultSet nuovafoto(String nome, Boolean privata, Integer utente, String dispositivo, LocalDate data, Luogo luogo) {
        String query = "INSERT INTO Foto VALUES ('" + nome + "', DEFAULT, " + privata + ", " + utente + ", '" + dispositivo + "', '" + java.sql.Date.valueOf(data) + "', " + luogo.getIdLuogo() + ") RETURNING idFoto";
        try {
            Connessione db = Connessione.getInstanza();
            Connection connessione = db.connessione;
            PreparedStatement pstmt = connessione.prepareStatement(query);

            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void newPossiede (int idFoto, int idTema){
        String query =  "INSERT INTO Possiede VALUES('"+idFoto+"','"+idTema+"')";

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

