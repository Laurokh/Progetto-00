package com.galleria_fotografica.controller;


import com.galleria_fotografica.Main;
import com.galleria_fotografica.model.Utente;
import implementazioneDao.LoginDaoimpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {
    private @FXML TextField campoNome;
    private @FXML PasswordField campoPsw;

    private @FXML Label testo;

    private @FXML void login() throws SQLException { /* prende i campi nome e pass se coincidono spostati alla schermata galleria */
        Utente utente;
        Stage oldStage = (Stage) campoNome.getScene().getWindow();
        Stage newStage = new Stage();
        LoginDaoimpl dao = new LoginDaoimpl();
        ResultSet rs = dao.login(campoNome.getText(), campoPsw.getText());

        if (rs.next()) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Galleria.fxml"));
            utente = new Utente(rs.getString("username"), rs.getInt("idutente"));

            Scene scene ;
            try {
                fxmlLoader.setControllerFactory(e -> new GalleriaController(utente));
                scene = new Scene(fxmlLoader.load());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            newStage.setScene(scene);
            oldStage.close();


            newStage.setTitle("Galleria");
            newStage.setResizable(false);
            newStage.show();
        } else {
            testo.setText("Nome utente o Password errata");
        }
        ;
    }


}



