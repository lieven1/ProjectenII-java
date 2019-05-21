/*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
 */
package gui.Overzichten.Aanwezigheden;

import domain.GebruikerModels.AGebruiker;
import domain.GebruikerModels.Gebruiker;
import domain.GebruikerModels.ProefGebruiker;
import domain.Overzicht.Aanwezigheid;
import domain.Overzicht.Lesmoment;
import domain.Overzicht.LesmomentLeden;
import domain.controllers.GebruikerController;
import domain.controllers.OverzichtController;
import gui.Overzichten.DetailPanel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.value.ObservableValue;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author boris
 */
public class AanwezighedenDetailPanelController extends DetailPanel implements Initializable {

    private OverzichtController oc;
    private GebruikerController gc;

    @FXML
    private Label titelLabel;
    @FXML
    private TableView<Aanwezigheid> tableViewAanwezigheden;
    @FXML
    private TableColumn<Aanwezigheid, String> naamGebruiker;
    @FXML
    private TableColumn<Aanwezigheid, String> VoornaamGebruiker;
    @FXML
    private TableColumn<Aanwezigheid, String> AanwezigGebruiker;
    @FXML
    private TableColumn<Aanwezigheid, String> Ingeschreven;
    @FXML
    private Label detailGebruikerNaam;
    @FXML
    private Label detailTelefoonLabel;
    @FXML
    private Label detailTelefoonValue;
    @FXML
    private Label detailEmailLabel;
    @FXML
    private Label detailEmailValue;
    @FXML
    private Label detailGradatieLabel;
    @FXML
    private Label detailGradatieValue;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public AanwezighedenDetailPanelController(OverzichtController oc, GebruikerController gc) {
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
            AGebruiker gebr = gebruikers.stream().filter(g -> g.getGebruikersnaam().toLowerCase().equals(entry.getGebruikersnaam().toLowerCase())).findFirst().orElse(null);
            if (gebr != null) {
                if (gebr instanceof ProefGebruiker) {
                    aanwezigheden.add(new Aanwezigheid(gebr.getNaam(), gebr.getVoornaam(), entry.getAanwezig(), entry.getIngeschreven(), gebr.getEmail(), gebr.getTelefoonnummer(), "Proefgebruiker"));
                } else {
                    Gebruiker gebrR = (Gebruiker) gebr;
                    aanwezigheden.add(new Aanwezigheid(gebr.getNaam(), gebr.getVoornaam(), entry.getAanwezig(), entry.getIngeschreven(), gebr.getEmail(), gebr.getTelefoonnummer(), gebrR.getGraad().name()));
                }
                gebruikers.removeIf(g -> g.getGebruikersnaam().toLowerCase().equals(gebr.getGebruikersnaam().toLowerCase()));
            }
        });

        //tableview opvullen
        tableViewAanwezigheden.setEditable(false);
        naamGebruiker.setCellValueFactory(new PropertyValueFactory<>("naam"));
        VoornaamGebruiker.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        AanwezigGebruiker.setCellValueFactory(new PropertyValueFactory<>("aanwezig"));
        Ingeschreven.setCellValueFactory(new PropertyValueFactory<>("ingeschreven"));
        tableViewAanwezigheden.setItems(FXCollections.observableArrayList(aanwezigheden));
        AanwezigGebruiker.setStyle("-fx-alignment: CENTER;");
        Ingeschreven.setStyle("-fx-alignment: CENTER;");
    }

    @FXML
    private void mouseClickItem(MouseEvent event) {
        Aanwezigheid aanwezigheid = tableViewAanwezigheden.getSelectionModel().getSelectedItem();
        detailGebruikerNaam.setText(aanwezigheid.getNaam() + " " + aanwezigheid.getVoornaam());
        detailEmailValue.setText(aanwezigheid.getEmail());
        detailTelefoonValue.setText(aanwezigheid.getTelefoon());
        detailGradatieValue.setText(aanwezigheid.getGradatie());

    }

}
