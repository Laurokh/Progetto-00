package com.galleria_fotografica.model;


import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class Foto {
    private String id;
    private String dispositivo;
    private boolean privata;
    private LocalDate data_scatto;
    private Luogo luogo;
    private Utente scattata_da;

    private FileChooser iFile;
    private ArrayList<Compone> compone = new ArrayList<>();
    private ArrayList<Tema> temi = new ArrayList<>();
    private ArrayList<Utente> rappresenta = new ArrayList<>();


    public Foto(String id, String dispositivo, boolean privata, LocalDate data_scatto) {
        this.id = id;
        this.dispositivo = dispositivo;
        this.privata = privata;
        this.data_scatto = data_scatto;


    }


    public void setscattata_da(Utente scattata_da) {
        this.scattata_da = scattata_da;
    }

    public void addtema(Tema tema) {
        this.temi.add(tema);
    }

    public void addcompone(Compone compone) {
        this.compone.add(compone);
    }

    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }

    public void setrappresenta(Utente rappresenta) {
        this.rappresenta.add(rappresenta);
    }

}
