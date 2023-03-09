package com.galleria_fotografica.controller;

import com.galleria_fotografica.model.Foto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

public class NuovaFotoController {
    private @FXML MenuButton dispositivoMenuButton;
    ;
    private Foto foto=new Foto();




    private @FXML void fotocamera() {
        dispositivoMenuButton.setText("Fotocamera");
         foto.setDispositivo("Fotocamera");

    }

    private @FXML void cellulare() {
        dispositivoMenuButton.setText("Cellulare");
        foto.setDispositivo("Cellulare");
    }


    public void setPrivata() {
        foto.setPrivata(true);
    }

    public void setPublica() {
        foto.setPrivata(false);
    }

    private @FXML void conferma() {

        Stage stage = (Stage) dispositivoMenuButton.getScene().getWindow();

        stage.close();
    }


    private @FXML void annulla() {
        Stage stage = (Stage) dispositivoMenuButton.getScene().getWindow();
        stage.close();
    }
}


