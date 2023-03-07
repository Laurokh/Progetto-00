package implementazioneDao;


import DAO.LoginDAO;
import com.galleria_fotografica.connections.Connessione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOimpl implements LoginDAO {

    public ResultSet login(String nome, String pass) {
        String query = "SELECT * FROM Utente WHERE username = '" + nome + "' AND password = '" + pass + "'";

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