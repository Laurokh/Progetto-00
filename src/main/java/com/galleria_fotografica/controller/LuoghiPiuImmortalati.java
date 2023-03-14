package com.galleria_fotografica.controller;

import com.galleria_fotografica.model.Tabella;
import implementazioneDao.GalleriaDaoimpl;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class LuoghiPiuImmortalati {
    private @FXML Button Chiudi;
    private @FXML TableView<Tabella> tabella;

    private @FXML void initialize() {

        GalleriaDaoimpl foto = new GalleriaDaoimpl();
        ResultSet tab = foto.luoghiPiuImmoratlati();
        ArrayList<Tabella> tabellaDaVisualizzare= new ArrayList<>();

        try {
            while (tab.next()) {
                tabellaDaVisualizzare.add(new Tabella(
                                tab.getString("luogo"),
                                tab.getInt("immortalazioni")
                        )
                );


            }
        } catch (SQLException ignore) {
        }


        tabella.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("luogo"));
        tabella.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("immortalazioni"));

        tabella.getItems().addAll(tabellaDaVisualizzare);

    }


    private @FXML void chiudiScheda() {
        Stage stage = (Stage) Chiudi.getScene().getWindow();
        stage.close();
    }
}
