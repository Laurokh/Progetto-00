package com.galleria_fotografica.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Collezione {
    private String nome;
    private String id;
    private LocalDate data_creazione;


//
    private ArrayList <Compone> compone= new ArrayList<>();

    public void addcompone(Compone compone) {

        this.compone.add(compone);
    }

    public Collezione(String nome, String id, LocalDate data_creazione) {
        this.nome = nome;
        this.id = id;
        this.data_creazione = data_creazione;
    }
}
