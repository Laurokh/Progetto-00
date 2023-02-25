package com.galleria_fotografica.model;

import java.util.ArrayList;

public class Tema {
    private String id;
    private String descrizione;
    private String nome;

    private ArrayList<Foto> foto = new ArrayList<>();

    public void addfoto(Foto foto) {

        this.foto.add(foto);
    }

    public Tema(String id, String descrizione, String nome) {
        this.id = id;
        this.descrizione = descrizione;
        this.nome = nome;
    }
}
