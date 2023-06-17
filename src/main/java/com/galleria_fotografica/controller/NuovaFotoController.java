package com.galleria_fotografica.controller;

import com.galleria_fotografica.model.Foto;
import com.galleria_fotografica.model.Luogo;
import com.galleria_fotografica.model.Utente;
import implementazioneDao.LuogoDaoimpl;
import implementazioneDao.NuovaFotoDaoimpl;
import implementazioneDao.TemaDaoimpl;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NuovaFotoController {
    private @FXML MenuButton dispositivoMenuButton;
    private @FXML Button selFoto;
    private @FXML DatePicker dataScatto;
    private @FXML MenuButton temalbl;
    private @FXML MenuButton luogolbl;
    private @FXML TextField latLbl;
    private @FXML TextField longLbl;
    private @FXML AnchorPane Testo;
    private @FXML Label errore;
    private Foto foto = new Foto();
    List<Integer> idTemi = new ArrayList<>();


    private  @FXML TextField nome;

    private @FXML ImageView imageView;

    private Utente utente;

    private @FXML void initialize() {

    TemaDaoimpl temaDao = new TemaDaoimpl();
    LuogoDaoimpl luogoDao = new LuogoDaoimpl();
    ResultSet rs = temaDao.caricaTemi();
    ResultSet rs2 = luogoDao.caricaLuogo();
    try {
        while (rs2.next()) {
            String nomeLuogo = rs2.getString("nome");
            double lat = rs2.getDouble("latitudine");
            double lng = rs2.getDouble("longitudine");
            int id = rs2.getInt("idluogo");

            MenuItem menuItem = new MenuItem(nomeLuogo);
            menuItem.setOnAction(actionEvent -> {
                luogolbl.setText(nomeLuogo);

                foto.setLuogo(new Luogo(nomeLuogo, lat, lng, id));
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
            int idTema = rs.getInt("idTema");

            CheckMenuItem menuItem = new CheckMenuItem(nomeTema);
            menuItem.setOnAction(actionEvent -> {
                if (menuItem.isSelected()) {
                    foto.addtema(idTema);
                } else {
                    foto.togliTema(idTema);

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


        int fotoId = 0;
        NuovaFotoDaoimpl fotoDao = new NuovaFotoDaoimpl();
        Stage stage = (Stage) dispositivoMenuButton.getScene().getWindow();
        foto.setDataScatto(dataScatto.getValue());
        utente= (Utente)stage.getUserData();

       // try {
            ResultSet rs= fotoDao.nuovafoto(nome.getText(), foto.isPrivata(), utente.getId(), foto.getDispositivo(), foto.getData_scatto(), foto.getLuogo());
            
            try{
            if(rs.next()) {fotoId = rs.getInt("idFoto");}}catch(SQLException e){}
            System.out.println(fotoId);
                for (int id : idTemi) {
                    fotoDao.newPossiede(fotoId,id);
                    System.out.println("sec");
                }

                /*}catch (Exception e){
            errore.setText("Errore!!");

            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                errore.setText("");
            });
            pause.play();
            return;}*/
            





        stage.close();
        stage.setUserData(foto);
    }


    private @FXML void annulla() {
        Stage stage = (Stage) dispositivoMenuButton.getScene().getWindow();
        stage.close();
    }

    private @FXML void sFoto() {
        String url = foto.scegliFoto();
        if(url == null) return;

        imageView.setImage(new Image(url));
        Testo.setVisible(false);
    }


}


