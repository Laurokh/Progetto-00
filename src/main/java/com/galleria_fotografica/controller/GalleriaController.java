package com.galleria_fotografica.controller;

import com.galleria_fotografica.model.Tabella;
import com.galleria_fotografica.Main;
import com.galleria_fotografica.model.*;
import implementazioneDao.GalleriaDaoimpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private @FXML MenuButton collezioni;
    private @FXML TableView<TabellaFoto> lista;



    private ArrayList<Foto> listaFoto = new ArrayList<>();

    private ArrayList<Collezione> listaCollezioni = new ArrayList<>();
    private ArrayList<Luogo> listaLuoghi = new ArrayList<>();
    private ArrayList<Tema> listaTemi = new ArrayList<>();


    private final Utente utente;

    public GalleriaController(Utente utente) {
        this.utente = utente;
    }

    private @FXML void initialize() {
        nomeUtenteLabel.setText(utente.getNickname());
        GalleriaDaoimpl galleriaDao = new GalleriaDaoimpl();
        ResultSet listaTemiDao = galleriaDao.listaTemi();
        ResultSet listaLuoghiDao = galleriaDao.listaLuoghi();
        ResultSet listaFotoDao = galleriaDao.listaFoto(utente.getId());
        ResultSet listaCollezioniDao = galleriaDao.listaCollezioni(utente.getId());
        ArrayList<TabellaFoto> listaF = new ArrayList<>();


        try {

            while (listaLuoghiDao.next()) {
                listaLuoghi.add(new Luogo(
                        listaLuoghiDao.getString("nome"),
                        listaLuoghiDao.getDouble("latitudine"),
                        listaLuoghiDao.getDouble("longitudine"),
                        listaLuoghiDao.getInt("idluogo")
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

            while (listaTemiDao.next()) {
                listaTemi.add(new Tema(
                        listaTemiDao.getInt("idtema"),
                        listaTemiDao.getString("descrizione"),
                        listaTemiDao.getString("nome")
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

            while (listaFotoDao.next()) {
                listaFoto.add(new Foto(
                        listaFotoDao.getString("nome"),
                        listaFotoDao.getInt("idfoto"),
                        listaFotoDao.getString("dispositivo"),
                        listaFotoDao.getBoolean("privata"),
                        listaFotoDao.getDate("data_scatto").toLocalDate()
                ));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        ResultSet listaDao = galleriaDao.listaFoto(utente.getId());

        try {
            while (listaDao.next()) {

                listaF.add(new TabellaFoto( listaDao.getString("nome")));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lista.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        lista.getItems().addAll(listaF);

        try {
            while (listaCollezioniDao.next()) {
                listaCollezioni.add(new Collezione(
                        listaCollezioniDao.getString("nome"),
                        listaCollezioniDao.getInt("id"),
                        listaCollezioniDao.getDate("data_creazione").toLocalDate()
                        ));
                String nomeCollezione = listaCollezioniDao.getString("nome");
                MenuItem collezione = new MenuItem(nomeCollezione);


                collezioni.getItems().add(collezione);
            }

        }catch (SQLException e) {
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
        newStage.setUserData(utente);
        newStage.showAndWait();
        listaFoto.add((Foto) newStage.getUserData());


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
        newStage.showAndWait();
        listaCollezioni.add((Collezione) newStage.getUserData());

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
