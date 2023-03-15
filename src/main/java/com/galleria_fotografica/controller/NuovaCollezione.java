package com.galleria_fotografica.controller;


import com.galleria_fotografica.model.Collezione;
import implementazioneDao.NuovaCollezioneDaoimpl;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class NuovaCollezione {

    private @FXML TextField nomeCollezione;
    private @FXML MenuButton listaUtenti;
    private Collezione collezione = new Collezione();


    private @FXML void initialize() {
        NuovaCollezioneDaoimpl dao = new NuovaCollezioneDaoimpl();
        ResultSet listautenti = dao.listautenti();
        try {

            do {
                String nomeUtente = listautenti.getString("username");
                CheckMenuItem utente = new CheckMenuItem(nomeUtente);
                utente.setOnAction(actionEvent -> {
                    if (utente.isSelected()) {
                        collezione.addutente(nomeUtente);
                    } else {
                        collezione.togliutente(nomeUtente);


                    }
                });
                listaUtenti.getItems().add(utente);
            }while (listautenti.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //  Label utente = new Label(nomeUtente);
        // selezioneUtenti.getSelectionModel().selectionModeProperty().addListener((ObservableValue<? extends String>, utente.setText(nomeUtente);));
//vfd
    }


    private @FXML void conferma() {
        LocalDate date = LocalDate.now();
        NuovaCollezioneDaoimpl nuovaCollezione = new NuovaCollezioneDaoimpl();
        Stage stage = (Stage) listaUtenti.getScene().getWindow();

        nuovaCollezione.nuovaCollezione(nomeCollezione.getText(),date);


        stage.close();
    }


    public void annulla() {
        Stage stage = (Stage) listaUtenti.getScene().getWindow();
        stage.close();
    }
}
