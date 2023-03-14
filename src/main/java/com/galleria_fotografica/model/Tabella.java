package com.galleria_fotografica.model;

public class Tabella {
    String luogo;
    int immortalazioni;


    public String getLuogo() {
        return luogo;
    }

    public int getImmortalazioni() {
        return immortalazioni;
    }

    public Tabella(String luogo, int immortalazioni) {
        this.luogo = luogo;
        this.immortalazioni = immortalazioni;
    }
}
