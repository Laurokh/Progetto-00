package com.galleria_fotografica.controller;

import com.galleria_fotografica.model.Foto;
import com.galleria_fotografica.model.Luogo;
import implementazioneDao.LoginDAOimpl;
import implementazioneDao.LuogoDaoimpl;
import implementazioneDao.TemaDAOimpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class NuovaFotoController {
    private @FXML MenuButton dispositivoMenuButton;
    private @FXML Button selFoto;
    private @FXML DatePicker dataScatto;
    private @FXML MenuButton temalbl;
    private @FXML MenuButton luogolbl;
    private @FXML TextField latLbl;
    private @FXML TextField longLbl;
    private Foto foto = new Foto();


    private @FXML void initialize() {

        TemaDAOimpl dao = new TemaDAOimpl();
        LuogoDaoimpl dao2 = new LuogoDaoimpl();
        ResultSet rs = dao.caricaTemi();
        ResultSet rs2 = dao2.caricaLuogo();
        try {
            while (rs2.next()) {
                String nomeLuogo = rs2.getString("nome");
                double lat = rs2.getDouble("latitudine");
                double lng = rs2.getDouble("longitudine");

                MenuItem menuItem = new MenuItem(nomeLuogo);
                menuItem.setOnAction(actionEvent -> {
                    luogolbl.setText(nomeLuogo);

                    foto.setLuogo(new Luogo(nomeLuogo, lat, lng));
                    latLbl.setText(String.valueOf(lat));
                    longLbl.setText(String.valueOf(lng));
                });

                luogolbl.getItems().add(menuItem);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            while (rs.next()) {
                String nomeTema = rs.getString("nome");

                CheckMenuItem menuItem = new CheckMenuItem(nomeTema);
                menuItem.setOnAction(actionEvent -> {
                    if (menuItem.isSelected()) {
                        foto.addtema(nomeTema);
                    } else {
                        foto.togliTema(nomeTema);

                    }
                });
                temalbl.getItems().add(menuItem);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    private @FXML void fotocamera() {
        dispositivoMenuButton.setText("Fotocamera");
        foto.setDispositivo("Fotocamera");

    }

    private @FXML void cellulare() {
        dispositivoMenuButton.setText("Cellulare");
        foto.setDispositivo("Cellulare");
    }


    public void setPrivata() {
        foto.setPrivata(true);
    }

    public void setPublica() {
        foto.setPrivata(false);
    }


    private @FXML void conferma() {

        Stage stage = (Stage) dispositivoMenuButton.getScene().getWindow();

        foto.setDataScatto(dataScatto.getValue());

        stage.close();
    }


    private @FXML void annulla() {
        Stage stage = (Stage) dispositivoMenuButton.getScene().getWindow();
        stage.close();
    }

    private @FXML void sFoto () { foto.scegliFoto();   }


}
//todo finisci l'aggiunta della foto, funzione di inserimento nel model e nel database


