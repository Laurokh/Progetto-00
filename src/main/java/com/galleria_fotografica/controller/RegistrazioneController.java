package com.galleria_fotografica.controller;

import implementazioneDao.LoginDaoimpl;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class RegistrazioneController {


    private @FXML TextField campoNome;

    private @FXML PasswordField campoPsw;


    private @FXML Label testo;

    private LoginDaoimpl dao = new LoginDaoimpl();
    private Stage oldStage;


    private @FXML void registrazione() {

        dao.registrazione(campoNome.getText(), campoPsw.getText());
        oldStage = (Stage) campoPsw.getScene().getWindow();
        oldStage.close();
    }
    private @FXML void annulla() {
        Stage stage = (Stage) campoPsw.getScene().getWindow();
        stage.close();
    }

}

