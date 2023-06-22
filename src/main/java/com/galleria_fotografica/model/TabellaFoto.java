package com.galleria_fotografica.model;

public class TabellaFoto {
    private int id;
    private String nome;

    public TabellaFoto(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }}
