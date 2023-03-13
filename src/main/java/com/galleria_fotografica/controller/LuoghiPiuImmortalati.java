package com.galleria_fotografica.controller;

import implementazioneDao.GalleriaDaoimpl;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LuoghiPiuImmortalati {
    private @FXML Button Chiudi;
    private @FXML TableView tabella;
    private @FXML TableColumn nome;
    private @FXML TableColumn nFoto;


    private @FXML void initialize() {
        GalleriaDaoimpl foto = new GalleriaDaoimpl();
        ResultSet tab = foto.luoghiPiuImmoratlati();



        try {
            ObservableList<ResultSet> valori = FXCollections.observableArrayList();
            while (tab.next()){


            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
