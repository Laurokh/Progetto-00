package com.galleria_fotografica.controller;

import implementazioneDao.GalleriaDaoimpl;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class LuoghiPiuImmortalati {
    private @FXML Button Chiudi;
    private @FXML TableView<Map<String, Object>> tabella;

    private @FXML void initialize() {
        TableColumn<Map<String, Object>, String> luogoColonna = new TableColumn<>("Luogo");
        luogoColonna.setCellValueFactory(data -> new SimpleStringProperty((String) data.getValue().get("luogo")));

        TableColumn<Map<String, Object>, Integer> immortalazioniColonna = new TableColumn<>("Immortalazioni");
        immortalazioniColonna.setCellValueFactory(data -> new SimpleObjectProperty<>((Integer) data.getValue().get("immortalazioni")));

        tabella.getColumns().addAll(luogoColonna, immortalazioniColonna);

        ObservableList<Map<String, Object>> tabellaDaVisualizzare = getDatiTabella();

        tabella.setItems(tabellaDaVisualizzare);
    }

    private ObservableList<Map<String, Object>> getDatiTabella() {
        GalleriaDaoimpl foto = new GalleriaDaoimpl();

        ObservableList<Map<String, Object>> tabellaDaVisualizzare = FXCollections.observableArrayList();

        try {
            ResultSet tab = foto.luoghiPiuImmoratlati();
            while (tab.next()) {

                String luogo = tab.getString("Nome");
                int immortalazioni = tab.getInt("Immortalazioni");

                Map<String, Object> riga = new HashMap<>();
                riga.put("luogo", luogo);
                riga.put("immortalazioni", immortalazioni);

                tabellaDaVisualizzare.add(riga);
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
