package implementazioneDao;


import DAO.LoginDAO;
import com.galleria_fotografica.connections.Connessione;

import java.sql.*;

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

    public void registrazione(String nome, String pass){

        String query = "INSERT INTO Utente VALUES (DEFAULT,'" + nome + "' ,'" + pass + "')";

        try {
            Connessione db = Connessione.getInstanza();
            Connection connessione = db.connessione;
            Statement statement = connessione.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

}}