package com.galleria_fotografica.controller;

import com.galleria_fotografica.Main;
import com.galleria_fotografica.model.*;
import implementazioneDao.GalleriaDaoimpl;
import implementazioneDao.NuovaCollezioneDaoimpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GalleriaController {
    private @FXML Label nomeUtenteLabel;
    private @FXML Button nuovaFoto;
    private @FXML Button nuovaCollezione;
    private @FXML MenuButton temi;
    private @FXML MenuButton luoghi;


    private ArrayList<Foto> listaFoto;
    private ArrayList<Collezione> listaCollezioni;
    private ArrayList<Luogo> listaLuoghi;
    private ArrayList<Tema> listaTemi;

    private Utente utente;

    private @FXML void initialize() {
        // utente = (Utente)nomeUtenteLabel.getScene().getWindow().getUserData();
        nomeUtenteLabel.setText("utente");
        GalleriaDaoimpl galleriaDao = new GalleriaDaoimpl();
        ResultSet listaTemi = galleriaDao.listaTemi();
        ResultSet listaLuoghi = galleriaDao.listaLuoghi();
        try {

            do {
                String nomeLuogo = listaLuoghi.getString("nome");
                MenuItem luogo = new MenuItem(nomeLuogo);
                luogo.setOnAction(actionEvent -> {
                    galleriaDao.ordinaPerLuogo(String.valueOf(luogo));
                });

                luoghi.getItems().add(luogo);
            } while (listaLuoghi.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {

            do {
                String nomeTema = listaTemi.getString("nome");
                MenuItem tema = new MenuItem(nomeTema);
                tema.setOnAction(actionEvent -> {
                    galleriaDao.ordinaPerTema(String.valueOf(tema));
                });

                temi.getItems().add(tema);
            } while (listaTemi.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    private @FXML void Luoghipiuimmortalati() {

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
