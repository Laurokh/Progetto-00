package com.galleria_fotografica.controller;


import DAO.LoginDAO;
import com.galleria_fotografica.Main;
import com.galleria_fotografica.model.Utente;
import implementazioneDao.LoginDAOimpl;
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
        LoginDAOimpl dao = new LoginDAOimpl();
        ResultSet rs = dao.login(campoNome.getText(), campoPsw.getText());

        if (rs != null) {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Galleria.fxml"));

            Scene scene = null;
            try {

                scene = new Scene(fxmlLoader.load());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            utente= new Utente(rs.getString("username"), rs.getInt("idutente"));

            newStage.setScene(scene);
            newStage.setUserData(utente);
            oldStage.close();


            newStage.setTitle("Galleria");
            newStage.setResizable(false);
            newStage.show();
        } else testo.setText("Nome utente o Password errata");
    }


}



