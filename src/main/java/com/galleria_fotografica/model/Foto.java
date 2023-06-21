package com.galleria_fotografica.model;


import javafx.stage.FileChooser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Foto {
    private int id;
    private String dispositivo;
    private boolean privata;
    private LocalDate data_scatto;
    private Luogo luogo;

    private String nome;

    private FileChooser File;

    private List<Integer> temi = new ArrayList<>();


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

    public int getId() {
        return id;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public boolean isPrivata() {
        return privata;
    }

    public LocalDate getData_scatto() {
        return data_scatto;
    }

    public String getNome() {return nome;}
    public Luogo getLuogo() {
        return luogo;
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


    public Foto(String nome,int id, String dispositivo, boolean privata, LocalDate data_scatto) {
        this.nome= nome ;
        this.id = id;
        this.dispositivo = dispositivo;
        this.privata = privata;
        this.data_scatto = data_scatto;

    }




    public void addtema(int tema) {
        this.temi.add(tema);
    }

    public void togliTema(int tema) {
        this.temi.removeIf(item -> item.equals(tema));
    }



    public void setLuogo(Luogo luogo) {
        this.luogo = luogo;
    }





}
