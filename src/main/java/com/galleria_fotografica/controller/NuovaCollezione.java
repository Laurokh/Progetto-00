package com.galleria_fotografica.controller;



import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

public class NuovaCollezione {
    private @FXML MenuButton collezioneMenuButton;



    private @FXML void conferma() {
        Stage stage = (Stage) collezioneMenuButton.getScene().getWindow();
        stage.close();
    }


    public void annulla() {
        Stage stage = (Stage)collezioneMenuButton.getScene().getWindow();
        stage.close();
    }
}
