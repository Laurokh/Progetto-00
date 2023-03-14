package com.galleria_fotografica.model;

import java.util.ArrayList;

public class Luogo {
    private int idLuogo;

    public int getIdLuogo() {
        return idLuogo;
    }

    private String nome;
    private double latitudine;
    private double longitudine;

    private ArrayList <Foto> foto = new ArrayList<>();

    public void addFoto(Foto foto) {
        
        this.foto.add(foto);
    }

    public Luogo(String nome, double latitudine, double longitudine, int idLuogo) {
        this.nome = nome;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.idLuogo = idLuogo;
    }
}
