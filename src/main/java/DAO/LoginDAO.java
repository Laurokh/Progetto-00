package DAO;

import java.sql.ResultSet;

public interface LoginDAO {
     ResultSet login(String nome, String pass);
}
