package com.galleria_fotografica.model;


public class Tema {
    private int id;
    private String descrizione;
    private String nome;

    public int getId(){return id;}

    public Tema(int id, String descrizione, String nome) {
        this.id = id;
        this.descrizione = descrizione;
        this.nome = nome;
    }
}
