package com.galleria_fotografica.model;


import java.util.ArrayList;

public class Utente {
    private String nickname;
    private int id;
    private ArrayList<Foto> scatta = new ArrayList<>();
    private ArrayList<Foto> rappresentato = new ArrayList<>();

    public void setscatta(Foto scatta) {
        this.scatta.add(scatta);
    }

    public void setRappresentato(Foto rappresentato) {
        this.rappresentato.add(rappresentato);
    }

    public Utente(String nickname, int id) {
        this.nickname = nickname;
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getId() {
        return id;
    }

}
