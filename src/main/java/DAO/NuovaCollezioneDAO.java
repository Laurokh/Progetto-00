package DAO;

import java.sql.ResultSet;
import java.time.LocalDate;

public interface NuovaCollezioneDAO {
    ResultSet listautenti ();
    void nuovaCollezione(String nome, LocalDate data);


}
