package com.galleria_fotografica.controller;

import com.galleria_fotografica.model.Tabella;
import implementazioneDao.GalleriaDaoimpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class LuoghiPiuImmortalati {
    private @FXML Button Chiudi;
    private @FXML TableView<Tabella> tabella;

    private @FXML void initialize() {  TableColumn<Tabella, String> luogoColonna = new TableColumn<>("Luogo");
        luogoColonna.setCellValueFactory(new PropertyValueFactory<>("luogo"));

        TableColumn<Tabella, Integer> immortalazioniColonna = new TableColumn<>("Immortalazioni");
        immortalazioniColonna.setCellValueFactory(new PropertyValueFactory<>("immortalazioni"));

        tabella.getColumns().addAll(luogoColonna, immortalazioniColonna);

        ObservableList<Tabella> tabellaDaVisualizzare = getDatiTabella();

        tabella.setItems(tabellaDaVisualizzare);
    }

    private ObservableList<Tabella> getDatiTabella() {
        GalleriaDaoimpl foto = new GalleriaDaoimpl();
        ResultSet tab = foto.luoghiPiuImmoratlati();
        ObservableList<Tabella> tabellaDaVisualizzare = FXCollections.observableArrayList();

        try {
            while (tab.next()) {
                String luogo = tab.getString("Nome");
                int immortalazioni = tab.getInt("Immortalazioni");
                tabellaDaVisualizzare.add(new Tabella(luogo, immortalazioni));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tabellaDaVisualizzare;
    }




    private @FXML void chiudiScheda() {
        Stage stage = (Stage) Chiudi.getScene().getWindow();
        stage.close();
    }
}
