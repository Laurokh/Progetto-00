package com.galleria_fotografica.model;

public class Tabella {
    String luogo;
    int immortalazioni;

    public int getImmortalazioni() {
        return immortalazioni;
    }

    public String getLuogo() {
        return luogo;
    }


    public Tabella(String luogo, int immortalazioni) {
        this.luogo = luogo;
        this.immortalazioni = immortalazioni;
    }
}
