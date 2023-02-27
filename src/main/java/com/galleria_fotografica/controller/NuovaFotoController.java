package com.galleria_fotografica.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class NuovaFotoController {
    private @FXML MenuButton dispositivoMenuButton;
    private String dispositivoSelezionato = null;


    private Boolean privata ;


    private @FXML void fotocamera() {
        dispositivoMenuButton.setText("Fotocamera");
        dispositivoSelezionato = "Fotocamera";

    }

    private @FXML void cellulare() {
        dispositivoMenuButton.setText("Cellulare");
        dispositivoSelezionato = "Cellulare";
    }


    public void setPrivata() {
        privata = true;
    }

    public void setPublica() {
        privata = false;
    }
}


