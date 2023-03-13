package com.galleria_fotografica.connections;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {
    public Connection connessione=null;
    private static Connessione istanza;

    private Connessione() throws SQLException {
        String driver= "org.postgresql.Driver";
        String url= "jdbc:postgresql://localhost:2232/dataObj";
        String username= "postgres";
        String psw= "2678";
//
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        connessione=DriverManager.getConnection(url,username,psw);


    }

    public static Connessione getInstanza() throws SQLException {
        if (istanza==null){
            istanza= new Connessione();
        }
        else if (istanza.connessione.isClosed()){
            istanza= new Connessione();
        }
        return istanza;
    }
}

//
//Dao
/*    String query= "la query";
            try {
            Connessione db = Connessione.getInstanza();
            ResultSet rs = db.connessione.createStatement().executeQuery(query);
            db.connessione.close();
            if (rs.next()) {
                return rs;
            } else {
                return null ;
            }

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
}*/