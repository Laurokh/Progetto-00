package DAO;

import java.sql.ResultSet;
import java.time.LocalDate;

public interface NuovafotoDao {
    ResultSet nuovafoto (Boolean privata, Integer utente, String dispositivo, LocalDate data, Integer luogo);
}
