package com.galleria_fotografica.model;

import java.util.ArrayList;

public class Tema {
    private int id;
    private String descrizione;
    private String nome;

    private ArrayList<Foto> foto = new ArrayList<>();

    public void addfoto(Foto foto) {

        this.foto.add(foto);
    }

    public int getId(){return id;}

    public Tema(int id, String descrizione, String nome) {
        this.id = id;
        this.descrizione = descrizione;
        this.nome = nome;
    }
}
