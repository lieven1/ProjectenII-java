/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten.Aanwezigheden;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Gebruiker;
import domain.Overzicht.Aanwezigheid;
import domain.controllers.GebruikerController;
import domain.controllers.OverzichtController;
import domain.Overzicht.Lesmoment;
import domain.Overzicht.LesmomentLeden;
import gui.Overzichten.DetailPanel;
import gui.Overzichten.DetailPanel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

/**
 * ✓
 *
 * @author boris
 */
public class AanwezighedenDetailPanel extends DetailPanel {

    private OverzichtController oc;
    private GebruikerController gc;

    @FXML
    Label detailGebruikerNaam, detailTelefoonValue, detailEmailValue, detailGradatieValue;
    @FXML
    TableView tableViewAanwezigheden;
    @FXML
    TableColumn naamGebruiker, VoornaamGebruiker, AanwezigGebruiker, Ingeschreven, LesformuleGebruiker;

    public AanwezighedenDetailPanel(OverzichtController oc, GebruikerController gc) {
        this.oc = oc;
        this.gc = gc;
        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("AanwezighedenDetailPanel.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void loadItems(Object obj) {
        Lesmoment lesmoment = (Lesmoment) obj;
        List<LesmomentLeden> lesmomentLeden = oc.getLesmomentLeden().stream().filter(l -> l.getLesmomentId().equals(lesmoment.getLesmomentId())).collect(Collectors.toList());
        List<AGebruiker> gebruikers = gc.getGebruikerLijst().stream().collect(Collectors.toList());

        List<Aanwezigheid> aanwezigheden = new ArrayList<>();

        lesmomentLeden.forEach(entry -> {
            //Gebruiker gebr = (Gebruiker) gebruikers.stream().filter(g -> g.getGebruikersnaam().toLowerCase().equals(entry.getGebruikersnaam().toLowerCase())).findFirst().get();
            // aanwezigheden.add(new Aanwezigheid(gebr.getNaam(), gebr.getVoornaam(), entry.getAanwezig(), entry.getIngeschreven(), gebr.getLesformule().toString(), gebr.getEmail(), gebr.getGsmnummer(), gebr.getGraad().name()));
            aanwezigheden.add(new Aanwezigheid("test", "test", "test", "test", "test", "test", "test", "test"));

        });
        tableViewAanwezigheden = new TableView<Aanwezigheid>();
        tableViewAanwezigheden.setEditable(false);
        naamGebruiker.setCellValueFactory(new PropertyValueFactory<>("naam"));
        VoornaamGebruiker.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        AanwezigGebruiker.setCellValueFactory(new PropertyValueFactory<>("aanwezig"));
        Ingeschreven.setCellValueFactory(new PropertyValueFactory<>("ingeschreven"));
        LesformuleGebruiker.setCellValueFactory(new PropertyValueFactory<>("lesformule"));
        tableViewAanwezigheden.setItems(FXCollections.observableArrayList(aanwezigheden));

        tableViewAanwezigheden.getSelectionModel().selectedItemProperty().addListener((ObservableValue observableValue, Object oldValue, Object newValue) -> {
            Aanwezigheid aanwezigheid = (Aanwezigheid) newValue;
            detailGebruikerNaam.setText(aanwezigheid.getNaam() + " " + aanwezigheid.getVoornaam());
            detailEmailValue.setText(aanwezigheid.getEmail());
            detailTelefoonValue.setText(aanwezigheid.getTelefoon());
            detailGradatieValue.setText(aanwezigheid.getGradatie());
        });

        //opvullen tableview 
        //klasse maken die gegevens lesmontlid en gebruier houdt --> vullend tableview adhv die klasse
    }

}
