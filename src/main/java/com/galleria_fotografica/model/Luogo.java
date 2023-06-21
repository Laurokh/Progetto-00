package com.galleria_fotografica.model;


public class Luogo {
    private int idLuogo;

    public int getIdLuogo() {
        return idLuogo;
    }

    private String nome;
    private double latitudine;
    private double longitudine;



    public Luogo(String nome, double latitudine, double longitudine, int idLuogo) {
        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.idLuogo = idLuogo;
    }
}
