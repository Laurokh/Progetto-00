package DAO;

import java.sql.ResultSet;

public interface TemaDao {

    ResultSet caricaTemi ();
    ResultSet nuovoTema (String nome,String descrizione);
}
