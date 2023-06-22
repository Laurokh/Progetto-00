package com.galleria_fotografica.model;

import java.util.Date;


public class Compone {
    private Date data_creazione;
    private int foto;
    private int collezione;

    public Compone(int foto, int collezione, java.sql.Date data_creazione) {
        this.data_creazione = data_creazione;
        this.foto = foto;
        this.collezione= collezione;
    }


}
