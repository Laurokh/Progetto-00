package com.galleria_fotografica.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class  Collezione {
    private String nome;
    private int id;
    private LocalDate data_creazione;


    //
    private ArrayList<String> compone = new ArrayList<String>();

    public Collezione() {

    }

    public void addutente(String compone) {

        this.compone.add(compone);
    }

    public void togliutente(String compone) {
        this.compone.removeIf(item -> item.equals(compone));
    }

    public Collezione(String nome, int id, LocalDate data_creazione) {
        this.nome = nome;
        this.id = id;
        this.data_creazione = data_creazione;
    }
}
