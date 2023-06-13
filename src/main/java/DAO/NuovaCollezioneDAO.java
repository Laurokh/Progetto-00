package DAO;

import java.sql.ResultSet;
import java.time.LocalDate;

public interface NuovaCollezioneDAO {
    ResultSet listautenti ();
    ResultSet nuovaCollezione(String nome, LocalDate data);

    ResultSet maxCollezione();

}
