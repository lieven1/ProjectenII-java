/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten.Raadplegingen;

import domain.GebruikerModels.AGebruiker;
import domain.LesmateriaalModels.Lesmateriaal;
import domain.LesmateriaalModels.Raadpleging;
import domain.controllers.GebruikerController;
import domain.controllers.LesmateriaalController;
import domain.controllers.RaadplegingController;
import gui.Overzichten.DetailPanel;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import domain.Overzicht.RaadplegingGegevens;
import java.io.IOException;
import java.time.LocalTime;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author boris
 */
public class RaadplegingDetailPanelController extends DetailPanel implements Initializable {

    private GebruikerController gc;

    @FXML
    private Label titelLabel;
    @FXML
    private TableView<RaadplegingGegevens> tableViewRaadplegingen;
    @FXML
    private TableColumn<RaadplegingGegevens, String> naamGebruiker;
    @FXML
    private TableColumn<RaadplegingGegevens, String> VoornaamGebruiker;
    @FXML
    private TableColumn<RaadplegingGegevens, String> tijdRaadpleging;

    public RaadplegingDetailPanelController(GebruikerController gc) {
        this.gc = gc;
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("RaadplegingDetailPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void loadItems(Object obj) {
        Lesmateriaal lesmateriaal = (Lesmateriaal) obj;
        List<AGebruiker> gebruikers = gc.getGebruikerLijst().stream().collect(Collectors.toList());
        List<RaadplegingGegevens> raadplegingen = new ArrayList<>();
        lesmateriaal.getRaadplegingen().forEach(r -> {
            AGebruiker gebr = gebruikers.stream().filter(g -> g.getGebruikersnaam().toLowerCase().equals(r.getGebruiker().getGebruikersnaam().toLowerCase())).findFirst().orElse(null);
            if (gebr != null) {
                raadplegingen.add(new RaadplegingGegevens(gebr.getNaam(), gebr.getVoornaam(), r.getTijdstip().toString()));
            }
        });
        //tableview opvullen
        tableViewRaadplegingen.setEditable(false);
        naamGebruiker.setCellValueFactory(new PropertyValueFactory<>("naamGebruiker"));
        VoornaamGebruiker.setCellValueFactory(new PropertyValueFactory<>("voornaamGebruiker"));
        tijdRaadpleging.setCellValueFactory(new PropertyValueFactory<>("datumRaadpleging"));
        tableViewRaadplegingen.setItems(FXCollections.observableArrayList(raadplegingen));
    }

}
