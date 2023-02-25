package com.galleria_fotografica.model;

import java.util.Date;

public class Compone {
    private Date data_creazione;
    private Foto foto;
    private Collezione collezione;

    public Compone(Date data_creazione, Foto foto, Collezione collezione) {
        this.data_creazione = data_creazione;
        this.foto = foto;
        this.collezione = collezione;
    }
}
