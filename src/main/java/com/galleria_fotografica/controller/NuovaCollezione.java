package com.galleria_fotografica.controller;


import com.galleria_fotografica.model.Collezione;
import com.galleria_fotografica.model.Utente;
import implementazioneDao.NuovaCollezioneDaoimpl;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NuovaCollezione {



    private @FXML TextField nomeCollezione;
    private @FXML MenuButton listaUtenti;

    private Collezione collezione = new Collezione();
    List<Integer> idUtenti = new ArrayList<>();
    List<String> nomeUtenti = new ArrayList<>();

    private final  Utente utente;
    public NuovaCollezione(Utente utente){this.utente= utente;}

    private @FXML void initialize() {


        NuovaCollezioneDaoimpl dao = new NuovaCollezioneDaoimpl();
        ResultSet lUtenti = dao.listautenti();
        String nomeUtenteLoggato = utente.getNickname();
        try {
            while (lUtenti.next()) {
                String nomeUtente = lUtenti.getString("username");
                int idUtente = lUtenti.getInt("idUtente");

                nomeUtenti.add(nomeUtente);
                idUtenti.add(idUtente);

                CheckMenuItem utente = new CheckMenuItem(nomeUtente);
                if (nomeUtente.equals(nomeUtenteLoggato)) {
                    utente.setSelected(true);
                    utente.setDisable(true);  // Disabilita la selezione dell'utente loggato
                }

                utente.setOnAction(actionEvent -> {
                    if (utente.isSelected()) {
                        collezione.addutente(nomeUtente);
                    } else {
                        collezione.togliutente(nomeUtente);
                    }
                });


                listaUtenti.getItems().add(utente);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        }










    private @FXML void conferma() {
        LocalDate date = LocalDate.now();
        NuovaCollezioneDaoimpl nuovaCollezione = new NuovaCollezioneDaoimpl();
        Stage stage = (Stage) listaUtenti.getScene().getWindow();

        nuovaCollezione.nuovaCollezione(nomeCollezione.getText(), date);


        for (int idUtente : idUtenti) {
            nuovaCollezione.newPartecipazione(idUtente);
        }

        stage.close();
    }


    public void annulla() {
        Stage stage = (Stage) listaUtenti.getScene().getWindow();
        stage.close();
    }
}
