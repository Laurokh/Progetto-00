package com.galleria_fotografica.controller;

import implementazioneDao.LoginDaoimpl;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;




public class RegistrazioneController {


    private @FXML TextField campoNome;

    private @FXML PasswordField campoPsw;


    private @FXML Label testo;

    private LoginDaoimpl dao = new LoginDaoimpl();
    private Stage oldStage;


    private @FXML void registrazione() {
        try {
            if (!campoNome.getText().isBlank() && !campoPsw.getText().isBlank()) {
                dao.registrazione(campoNome.getText(), campoPsw.getText());
                oldStage = (Stage) campoPsw.getScene().getWindow();
                oldStage.close();
            } else {
                // Messaggio di errore se i campi sono vuoti o contengono solo spazi vuoti
                testo.setText("I campi non possono essere vuoti");
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> testo.setText(""));
                pause.play();
            }
        } catch (Exception e) {
            // Gestione dell'errore
            testo.setText("Errore!!");
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> testo.setText(""));
            pause.play();

        }
    }

    private @FXML void annulla() {
        Stage stage = (Stage) campoPsw.getScene().getWindow();
        stage.close();
    }

}

