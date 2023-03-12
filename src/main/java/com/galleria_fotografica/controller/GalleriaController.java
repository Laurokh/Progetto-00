package com.galleria_fotografica.controller;

import com.galleria_fotografica.Main;
import com.galleria_fotografica.model.Utente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class GalleriaController {
    private @FXML Label nomeUtenteLabel;
    private @FXML Button go;
    private @FXML Button nuovaFoto;
    private @FXML Button nuovaCollezione;
    private @FXML MenuButton temi;
   //private @FXML MenuButton luoghi;
    private @FXML CheckMenuItem luoghiPiuImmortalati;

    private Utente utente;

    private @FXML void initialize() {
       // utente = (Utente)nomeUtenteLabel.getScene().getWindow().getUserData();
        nomeUtenteLabel.setText("utente");



    }


    private @FXML void nuovaFoto() {

        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Nuova foto.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newStage.setScene(scene);


        newStage.setTitle("Nuova foto");
        newStage.setResizable(false);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();

    }

    private @FXML void nuovaCollezione() {

        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Nuova collezione.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        newStage.setScene(scene);


        newStage.setTitle("Nuova collezione");
        newStage.setResizable(false);
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.show();

    }

    private @FXML void Luoghipiuimmortalati(){
        if (luoghiPiuImmortalati.isSelected()) {
            Stage newStage = new Stage();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LuoghiPiùImmortalati.fxml"));
            Scene scene = null;
            try {
                scene = new Scene(fxmlLoader.load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newStage.setScene(scene);


            newStage.setTitle("Luoghi Più Immortalati");
            newStage.setResizable(false);
            newStage.initModality(Modality.APPLICATION_MODAL);
            newStage.show();

        }
    }
}
