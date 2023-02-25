package com.galleria_fotografica.controller;


import com.galleria_fotografica.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    private @FXML TextField campoNome;
    private @FXML PasswordField campoPsw;


    private @FXML void login() { /* prende i campi nome e pass se coincidono spostati alla schermata galleria */

        Stage oldStage = (Stage) campoNome.getScene().getWindow();
        Stage newStage = new Stage();


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Galleria.fxml"));
        Scene scene = null;
        try {

            scene = new Scene(fxmlLoader.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        newStage.setScene(scene);
        oldStage.close();


        newStage.setTitle("Galleria");
        newStage.setResizable(false);
        newStage.show();

    }




}



