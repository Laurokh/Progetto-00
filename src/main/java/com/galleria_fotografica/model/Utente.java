package com.galleria_fotografica.model;


public class Utente {
    private String nickname;
    private int id;

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
