package com.galleria_fotografica.model;


import javafx.stage.FileChooser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Foto {
    private String id;
    private String dispositivo;
    private boolean privata;
    private LocalDate data_scatto;
    private Luogo luogo;


    private Utente scattata_da;

    private FileChooser File;
    private ArrayList<Compone> compone = new ArrayList<>();
    private ArrayList<String> temi = new ArrayList<String>();
    private ArrayList<Utente> rappresenta = new ArrayList<>();

    public Foto() {
    }


    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }

    public void setPrivata(boolean privata) {
        this.privata = privata;
    }

    public void setDataScatto(LocalDate data_scatto) {
        this.data_scatto = data_scatto;
    }

    public String scegliFoto() {

        FileChooser fileChooser = new FileChooser();


        FileChooser.ExtensionFilter imagesFilter = new FileChooser.ExtensionFilter("Immagini", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().addAll(imagesFilter);


        java.io.File foto = fileChooser.showOpenDialog(null);
        if (foto != null) {
            return foto.getPath();
        } else {
            return null;
        }
    }


    public Foto(int id, String dispositivo, boolean privata, LocalDate data_scatto) {

        this.id = String.valueOf(id);
        this.dispositivo = dispositivo;
        this.privata = privata;
        this.data_scatto = data_scatto;
        //todo vedi this.File= null;

    }


    public void setscattata_da(Utente scattata_da) {
        this.scattata_da = scattata_da;
    }

    public void addtema(String tema) {
        this.temi.add(tema);
    }

    public void togliTema(String tema) {
        this.temi.removeIf(item -> item.equals(tema));
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
