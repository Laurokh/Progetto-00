package com.galleria_fotografica.controller;



import com.galleria_fotografica.model.Utente;
import implementazioneDao.NuovaCollezioneDAOimpl;
import implementazioneDao.NuovaFotoDaoimpl;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

import java.sql.ResultSet;

public class NuovaCollezione {
    private @FXML MenuButton collezioneMenuButton;
    private @FXML  ListView selezioneUtenti;
    private @FXML initialize (){
        NuovaCollezioneDAOimpl dao = new NuovaCollezioneDAOimpl();
        ResultSet listautenti = dao.listautenti();

        while (listautenti.next()) {
            String nomeUtente = listautenti.getString("username");
            CheckMenuItem  utente = new CheckMenuItem(nomeUtente);
             selezioneUtenti.getSelectionModel().selectionModeProperty().addListener((ChangeListener) utente);
//todo vedi sopra se compila e seleziona , vedere poi la condizione di selezione
          //  Label utente = new Label(nomeUtente);
           // selezioneUtenti.getSelectionModel().selectionModeProperty().addListener((ObservableValue<? extends String>, utente.setText(nomeUtente);));

        }

    }

    private @FXML void conferma() {
        Stage stage = (Stage) collezioneMenuButton.getScene().getWindow();
        stage.close();
    }


    public void annulla() {
        Stage stage = (Stage)collezioneMenuButton.getScene().getWindow();
        stage.close();
    }
}
