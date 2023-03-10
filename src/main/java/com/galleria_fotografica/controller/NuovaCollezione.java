package com.galleria_fotografica.controller;


import com.galleria_fotografica.model.Collezione;
import implementazioneDao.NuovaCollezioneDAOimpl;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NuovaCollezione {
    private @FXML MenuButton collezioneMenuButton;
    private @FXML ListView selezioneUtenti;
    private Collezione collezione = new Collezione();

    private @FXML void initialize() {
        NuovaCollezioneDAOimpl dao = new NuovaCollezioneDAOimpl();
        ResultSet listautenti = dao.listautenti();
        try {

            while (listautenti.next()) {
                String nomeUtente = listautenti.getString("username");
                CheckMenuItem utente = new CheckMenuItem(nomeUtente);
                utente.setOnAction(actionEvent -> {
                    if (utente.isSelected()) {
                        collezione.addutente(nomeUtente);
                    } else {
                        collezione.togliutente(nomeUtente);


                    }
                });
                selezioneUtenti.getSelectionModel().selectionModeProperty().addListener((ChangeListener) utente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } //todo vedi sopra se compila e seleziona , vedere poi la condizione di selezione
        //  Label utente = new Label(nomeUtente);
        // selezioneUtenti.getSelectionModel().selectionModeProperty().addListener((ObservableValue<? extends String>, utente.setText(nomeUtente);));
//vfd
    }


    private @FXML void conferma() {
        Stage stage = (Stage) collezioneMenuButton.getScene().getWindow();
        stage.close();
    }


    public void annulla() {
        Stage stage = (Stage) collezioneMenuButton.getScene().getWindow();
        stage.close();
    }
}
