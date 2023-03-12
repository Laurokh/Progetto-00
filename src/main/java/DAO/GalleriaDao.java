package DAO;

import java.sql.ResultSet;

public interface GalleriaDao {
    ResultSet listaLuoghi();

    ResultSet luoghiPiuImmoratlati();

    ResultSet ordinaPerLuogo(String nomeLuogo);

    ResultSet ordinaPerTema (String nomeTema);

}
