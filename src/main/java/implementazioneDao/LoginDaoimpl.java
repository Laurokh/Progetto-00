package implementazioneDao;


import DAO.LoginDAO;
import com.galleria_fotografica.connections.Connessione;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDaoimpl implements LoginDAO {

    public ResultSet login(String nome, String pass) {
        String query = "SELECT * FROM Utente WHERE username = '" + nome + "' AND password = '" + pass + "'";

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


}