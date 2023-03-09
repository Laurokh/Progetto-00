package DAO;

import java.sql.ResultSet;
import java.time.LocalDate;

public interface NuovaCollezioneDAO {
    public ResultSet listautenti ();
    public ResultSet nuovaCollezione(String nome, LocalDate data);


}
