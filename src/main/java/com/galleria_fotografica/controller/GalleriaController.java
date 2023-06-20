package com.galleria_fotografica.controller;


import com.galleria_fotografica.Main;
import com.galleria_fotografica.model.*;
import implementazioneDao.GalleriaDaoimpl;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.postgresql.jdbc.PgResultSet;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GalleriaController {
    private @FXML Label nomeUtenteLabel;
    private @FXML Button nuovaFoto;
    private @FXML Button nuovaCollezione;
    private @FXML CheckBox privata;
    private @FXML MenuButton temi;
    private @FXML MenuButton luoghi;
    private @FXML MenuButton Collezioni;
    private @FXML TableView<TabellaFoto> lista;
    private @FXML MenuButton Collezioni2;
    private @FXML Label error;
    private @FXML Label nome;
    private  @FXML MenuItem annullaC;


    GalleriaDaoimpl galleriaDao = new GalleriaDaoimpl();
    ArrayList<TabellaFoto> listaF = new ArrayList<>();



    private ArrayList<Foto> listaFoto = new ArrayList<>();

    private ArrayList<Compone> listaC= new ArrayList<>();
    private ArrayList<Collezione> listaCollezioni = new ArrayList<>();
    private ArrayList<Luogo> listaLuoghi = new ArrayList<>();
    private ArrayList<Tema> listaTemi = new ArrayList<>();
    ArrayList<TabellaFoto> ordinaPerTema = new ArrayList<>();
    ArrayList<TabellaFoto> ordinaPerLuogo = new ArrayList<>();
    ArrayList<TabellaFoto> ordinaPerCollezione = new ArrayList<>();


    private final Utente utente;

    public GalleriaController(Utente utente) {this.utente = utente;}

    private @FXML void initialize() {
        nomeUtenteLabel.setText(utente.getNickname());
        ResultSet listaCompone= galleriaDao.listaCompone();
        ResultSet listaTemiDao = galleriaDao.listaTemi();
        ResultSet listaLuoghiDao = galleriaDao.listaLuoghi();
        ResultSet listaFotoDao = galleriaDao.listaFoto(utente.getId());
        ResultSet listaCollezioniDao = galleriaDao.listaCollezioni(utente.getId());




        lista.setOnMouseClicked(this::isprivate);


    try {
        while(listaCompone.next()){
            listaC.add(new Compone(listaCompone.getInt("idFoto"),

                    listaCompone.getInt("idCollezione"),
                    listaCompone.getDate("data_aggiunta"))

            );
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

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
                    ResultSet oLuogo = galleriaDao.ordinaPerLuogo(nomeLuogo, utente.getId());

                    try {
                        while (oLuogo.next()) {

                            ordinaPerLuogo.add(new TabellaFoto(oLuogo.getString("nome")));


                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    lista.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
                    lista.getItems().setAll(ordinaPerLuogo);
                    ordinaPerLuogo.clear();

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
                    ResultSet oTema = galleriaDao.ordinaPerTema(nomeTema, utente.getId());

                    try {
                        while (oTema.next()) {

                            ordinaPerTema.add(new TabellaFoto(oTema.getString("nome")));


                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    lista.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
                    lista.getItems().setAll(ordinaPerTema);
                    ordinaPerTema.clear();
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

                listaF.add(new TabellaFoto(listaDao.getString("nome")));

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
                        listaCollezioniDao.getInt("idCollezione"),
                        listaCollezioniDao.getDate("data_creazione").toLocalDate()
                ));

                String nomeCollezione = listaCollezioniDao.getString("nome");
                int idCollezione = listaCollezioniDao.getInt("idCollezione");

                MenuItem collezione2 = new MenuItem(nomeCollezione);

                collezione2.setOnAction(actionEvent -> {
                    int selectedIndex = lista.getSelectionModel().getSelectedIndex();
                    Foto daAggiungere= new Foto();
                    try {daAggiungere= listaFoto.get(selectedIndex);}catch(Exception e){}

                    try {
                        galleriaDao.aggiungiaCollezione(idCollezione, daAggiungere.getId());
                        if(privata.isSelected()){privata.setSelected(false);}
                       error.setText("Fatto!!");

                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {
                            error.setText("");
                        });
                        pause.play();
                    }catch (Exception e){
                        error.setText("Errore!!");

                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {
                            error.setText("");
                        });
                        pause.play();

                    }
                    listaC.add(new Compone(
                            daAggiungere.getId(),
                            idCollezione,
                            Date.valueOf(LocalDate.now())
                    ));
                });

                Collezioni2.getItems().add(collezione2);

                MenuItem collezione = new MenuItem(nomeCollezione);
                collezione.setOnAction(actionEvent -> {
                    ResultSet oCollezione = galleriaDao.ordinaPerCollezione(idCollezione);

                    nome.setText(collezione.getText());

                    try {
                        while (oCollezione.next()) {
                            ordinaPerCollezione.add(new TabellaFoto(oCollezione.getString("nome")));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Collezioni.setText(collezione.getText());
                    lista.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
                    lista.getItems().setAll(ordinaPerCollezione);
                    ordinaPerCollezione.clear();
                });

                Collezioni.getItems().add(collezione);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void isprivate(MouseEvent mouseEvent) {

        int sIndex = lista.getSelectionModel().getSelectedIndex();
        Foto isPrivata=new Foto();
         try {isPrivata = listaFoto.get(sIndex);}catch (Exception e){}
        if (isPrivata.isPrivata() == true) {

            if (sIndex >= 0) {
                privata.setSelected(true);

            }
        } else {privata.setSelected(false);}
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


        Object userData = newStage.getUserData();
        if (userData instanceof Foto) {
            Foto foto = (Foto) userData;
            listaFoto.add(foto);
        }
        AggiornaLista ();

    }

    private @FXML void nuovaCollezione() {

        Stage newStage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Nuova collezione.fxml"));
        Scene scene ;
        try {
            fxmlLoader.setControllerFactory(e -> new NuovaCollezione(utente));
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
            ResultSet listaCollezioniDao= galleriaDao.listaCollezioni(utente.getId());

            Collezioni.getItems().remove(1, Collezioni.getItems().size());
          Collezioni2.getItems().clear();


        try {
            while (listaCollezioniDao.next()) {
                listaCollezioni.add(new Collezione(
                        listaCollezioniDao.getString("nome"),
                        listaCollezioniDao.getInt("idCollezione"),
                        listaCollezioniDao.getDate("data_creazione").toLocalDate()
                ));

                String nomeCollezione = listaCollezioniDao.getString("nome");
                int idCollezione = listaCollezioniDao.getInt("idCollezione");

                MenuItem collezione2 = new MenuItem(nomeCollezione);

                collezione2.setOnAction(actionEvent -> {
                    int selectedIndex = lista.getSelectionModel().getSelectedIndex();
                    Foto daAggiungere= listaFoto.get(selectedIndex);
                    try {
                        galleriaDao.aggiungiaCollezione(idCollezione, daAggiungere.getId());
                        if(privata.isSelected()){privata.setSelected(false);}
                        error.setText("Fatto!!");

                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {
                            error.setText("");
                        });
                        pause.play();
                    }catch (Exception e){
                        error.setText("Errore!!");

                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {
                            error.setText("");
                        });
                        pause.play();

                    }
                    listaC.add(new Compone(
                            daAggiungere.getId(),
                            idCollezione,
                            Date.valueOf(LocalDate.now())
                    ));
                });

                Collezioni2.getItems().add(collezione2);

                MenuItem collezione = new MenuItem(nomeCollezione);
                collezione.setOnAction(actionEvent -> {
                    ResultSet oCollezione = galleriaDao.ordinaPerCollezione(idCollezione);

                    nome.setText(collezione.getText());

                    try {
                        while (oCollezione.next()) {
                            ordinaPerCollezione.add(new TabellaFoto(oCollezione.getString("nome")));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Collezioni.setText(collezione.getText());
                    lista.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
                    lista.getItems().setAll(ordinaPerCollezione);
                    ordinaPerCollezione.clear();
                });

                Collezioni.getItems().add(collezione);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


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

    public void Ripristina() {

        lista.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        lista.getItems().setAll(listaF);
        Collezioni.setText("Lista Collezioni");
        nome.setText("");
    }


    public void EliminaFoto() {


        int selectedIndex = lista.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {

            Foto daEliminare = listaFoto.get(selectedIndex);
            listaFoto.remove(daEliminare);
            galleriaDao.eliminaFoto(String.valueOf(daEliminare.getId()));

            AggiornaLista();



        }
    }

    public void AggiornaLista (){

        listaF.clear();
        ResultSet listaDao = galleriaDao.listaFoto(utente.getId());

        try {
            while (listaDao.next()) {
                listaF.add(new TabellaFoto(listaDao.getString("nome")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lista.getItems().clear();
        lista.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
        lista.getItems().addAll(listaF);

    }
    public void Privata(){
        int sIndex = lista.getSelectionModel().getSelectedIndex();
        if (privata.isSelected() == true){

            if (sIndex >= 0) {

                Foto daPrivata = listaFoto.get(sIndex);
                galleriaDao.isPrivate(String.valueOf(daPrivata.getId()));
                daPrivata.setPrivata(true);


            }
        }else if (privata.isSelected() == false) {

            if (sIndex >= 0) {

                Foto daPrivata = listaFoto.get(sIndex);
                galleriaDao.isPubblica(String.valueOf(daPrivata.getId()));
                daPrivata.setPrivata(false);



            }
        }

}


    public void eliminaCollezione() {

        String elimina = Collezioni.getText();
        galleriaDao.eliminaCollezione(elimina);

        ResultSet listaCollezioniDao= galleriaDao.listaCollezioni(utente.getId());

        Collezioni.getItems().remove(1, Collezioni.getItems().size());
        Collezioni2.getItems().clear();


        try {
            while (listaCollezioniDao.next()) {
                listaCollezioni.add(new Collezione(
                        listaCollezioniDao.getString("nome"),
                        listaCollezioniDao.getInt("idCollezione"),
                        listaCollezioniDao.getDate("data_creazione").toLocalDate()
                ));

                String nomeCollezione = listaCollezioniDao.getString("nome");
                int idCollezione = listaCollezioniDao.getInt("idCollezione");

                MenuItem collezione2 = new MenuItem(nomeCollezione);

                collezione2.setOnAction(actionEvent -> {
                    int selectedIndex = lista.getSelectionModel().getSelectedIndex();
                    Foto daAggiungere= listaFoto.get(selectedIndex);
                    try {
                        galleriaDao.aggiungiaCollezione(idCollezione, daAggiungere.getId());
                        if(privata.isSelected()){privata.setSelected(false);}
                        error.setText("Fatto!!");

                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {
                            error.setText("");
                        });
                        pause.play();
                    }catch (Exception e){
                        error.setText("Errore!!");

                        PauseTransition pause = new PauseTransition(Duration.seconds(3));
                        pause.setOnFinished(event -> {
                            error.setText("");
                        });
                        pause.play();

                    }
                    listaC.add(new Compone(
                            daAggiungere.getId(),
                            idCollezione,
                            Date.valueOf(LocalDate.now())
                    ));
                });

                Collezioni2.getItems().add(collezione2);

                MenuItem collezione = new MenuItem(nomeCollezione);
                collezione.setOnAction(actionEvent -> {
                    ResultSet oCollezione = galleriaDao.ordinaPerCollezione(idCollezione);

                    nome.setText(collezione.getText());

                    try {
                        while (oCollezione.next()) {
                            ordinaPerCollezione.add(new TabellaFoto(oCollezione.getString("nome")));
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    Collezioni.setText(collezione.getText());
                    lista.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nome"));
                    lista.getItems().setAll(ordinaPerCollezione);
                    ordinaPerCollezione.clear();
                });

                Collezioni.getItems().add(collezione);


               Ripristina();

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }}


