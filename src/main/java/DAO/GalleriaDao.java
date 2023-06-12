package DAO;

import java.sql.ResultSet;

public interface GalleriaDao {
    ResultSet listaLuoghi();

    ResultSet listaTemi();

    ResultSet luoghiPiuImmoratlati();

    ResultSet ordinaPerLuogo(String nomeLuogo, int id);

    ResultSet ordinaPerTema (String nomeTema, int id);

    ResultSet listaCollezioni (int id);

    ResultSet listaFoto(int uId);

    ResultSet ordinaPerCollezione (int idCollezione);

    ResultSet isPubblica (String id);
    ResultSet isPrivate (String id);

    ResultSet listaCompone();

    ResultSet aggiungiaCollezione (int idCollezione, int idFoto);

    ResultSet eliminaFoto(String id);

}
