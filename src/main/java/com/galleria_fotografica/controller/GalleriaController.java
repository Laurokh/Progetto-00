package com.galleria_fotografica.controller;

import com.galleria_fotografica.Main;
import com.galleria_fotografica.model.*;
import implementazioneDao.GalleriaDaoimpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GalleriaController {
    private @FXML Label nomeUtenteLabel;
    private @FXML Button nuovaFoto;
    private @FXML Button nuovaCollezione;
    private @FXML MenuButton temi;
    private @FXML MenuButton luoghi;


    private ArrayList<Foto> listaFoto= new ArrayList<>();
    private ArrayList<Collezione> listaCollezioni=new ArrayList<>();
    private ArrayList<Luogo> listaLuoghi=new ArrayList<>();
    private ArrayList<Tema> listaTemi=new ArrayList<>();

    private final Utente utente;

    public GalleriaController(Utente utente) {
        this.utente = utente;
    }

    private @FXML void initialize() {
        nomeUtenteLabel.setText(utente.getNickname());
        GalleriaDaoimpl galleriaDao = new GalleriaDaoimpl();
        ResultSet listaTemiDao = galleriaDao.listaTemi();
        ResultSet listaLuoghiDao = galleriaDao.listaLuoghi();
        ResultSet listaFotoDao= galleriaDao.listaFoto(utente.getId());
        try {

            while (listaLuoghiDao.next()) {
                listaLuoghi.add(new Luogo(
                   listaLuoghiDao.getString("nome"),
                   listaLuoghiDao.getDouble("latitudine"),
                   listaLuoghiDao.getDouble("longitudine")
                ));

                String nomeLuogo = listaLuoghiDao.getString("nome");
                MenuItem luogo = new MenuItem(nomeLuogo);
                luogo.setOnAction(actionEvent -> {
                    galleriaDao.ordinaPerLuogo(String.valueOf(luogo));
                });

                luoghi.getItems().add(luogo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {

            while (listaLuoghiDao.next()){
                listaTemi.add(new Tema(
                        listaLuoghiDao.getInt("idluogo"),
                        listaLuoghiDao.getString("descrizione"),
                        listaLuoghiDao.getString("nome")
                ));

                String nomeTema = listaTemiDao.getString("nome");
                MenuItem tema = new MenuItem(nomeTema);

                tema.setOnAction(actionEvent -> {
                    galleriaDao.ordinaPerTema(String.valueOf(tema));
                });

                temi.getItems().add(tema);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {

            while (listaFotoDao.next()){
                listaFoto.add(new Foto(
                        listaFotoDao.getInt("idfoto"),
                        listaFotoDao.getString("dispositivo"),
                        listaFotoDao.getBoolean("privata"),
                        listaFotoDao.getDate("data_scatto").toLocalDate()
                ));
            }

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
        newStage.showAndWait();
        listaFoto.add((Foto)newStage.getUserData());


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
