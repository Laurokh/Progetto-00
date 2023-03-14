package DAO;

import com.galleria_fotografica.model.Luogo;

import java.time.LocalDate;

public interface NuovafotoDao {
    void nuovafoto (Boolean privata, Integer utente, String dispositivo, LocalDate data, Luogo luogo);
}
