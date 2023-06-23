package DAO;

import com.galleria_fotografica.model.Luogo;


import java.sql.ResultSet;
import java.time.LocalDate;

public interface NuovafotoDao {
    ResultSet nuovafoto (String nome, Boolean privata, Integer utente, String dispositivo, LocalDate data, Luogo luogo);
    void newPossiede (int idFoto, int idTema);
}
