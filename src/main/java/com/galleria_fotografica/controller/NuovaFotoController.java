package com.galleria_fotografica.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class NuovaFotoController {
    private @FXML MenuButton dispositivoMenuButton;
    private @FXML CheckBox ceckprivata;
    private String dispositivoSelezionato = null;

    private Boolean privata=false ;




    private @FXML void fotocamera() {
        dispositivoMenuButton.setText("Fotocamera");
        dispositivoSelezionato = "Fotocamera";

    }

    private @FXML void cellulare() {
        dispositivoMenuButton.setText("Cellulare");
        dispositivoSelezionato = "Cellulare";
    }


    private @FXML void isPrivate (){
    if(this.ceckprivata.isSelected()){
            privata=true;
        }

}

    public void setCeckprivata(CheckBox ceckprivata) {
        this.ceckprivata = ceckprivata;|
        
    }
}


