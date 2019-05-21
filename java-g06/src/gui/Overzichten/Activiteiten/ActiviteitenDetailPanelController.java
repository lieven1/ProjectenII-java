/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.Overzichten.Activiteiten;

import domain.Activiteit;
import domain.ActiviteitBegeleider;
import domain.Overzicht.ActiviteitGebruiker;
import domain.controllers.ActiviteitenController;
import domain.controllers.GebruikerController;
import domain.controllers.OverzichtController;
import gui.Overzichten.DetailPanel;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author boris
 */
public class ActiviteitenDetailPanelController extends DetailPanel implements Initializable {

    private OverzichtController oc;
    private ActiviteitenController ac;
    private GebruikerController gc;

    private final DateFormat dateFormat = new SimpleDateFormat("EEE, dd-MM-yyyy hh:mm", new Locale("nl", "BE"));

    @FXML
    private Label titelLabel;

    @FXML
    private Label lblStartdatum;
    @FXML
    private Label lblStartdatumValue;
    @FXML
    private Label lblEinddatum;
    @FXML
    private Label lblEinddatumValue;
    @FXML
    private Label lblDeelnemersTitel;
    @FXML
    private Label lblBegeleidersTitel;
    @FXML
    private TableView<ActiviteitGebruiker> tableViewBegeleidersActiviteit;
    @FXML
    private TableColumn<ActiviteitGebruiker, String> naamBegeleider;
    @FXML
    private TableColumn<ActiviteitGebruiker, String> VoornaamBegeleider;
    @FXML
    private TableColumn<ActiviteitGebruiker, String> TelefoonBegeleider;
    @FXML
    private TableColumn<ActiviteitGebruiker, String> EmailBegeleider;
    @FXML
    private TableView<ActiviteitGebruiker> tableViewAanwezighedenActiviteit;
    @FXML
    private TableColumn<ActiviteitGebruiker, String> naamGebruiker;
    @FXML
    private TableColumn<ActiviteitGebruiker, String> VoornaamGebruiker;
    @FXML
    private TableColumn<ActiviteitGebruiker, String> TelefoonGebruiker;
    @FXML
    private TableColumn<ActiviteitGebruiker, String> EmailGebruiker;
    @FXML
    private Label lblPlaatsenTitel;
    @FXML
    private Label lblPlaatsenIngenomen;
    @FXML
    private Label lblPlaatsenDivider;
    @FXML
    private Label lblMaxAantalPlaatsen;

    public ActiviteitenDetailPanelController(OverzichtController oc, ActiviteitenController ac, GebruikerController gc) {
        this.gc = gc;
        this.oc = oc;
        this.ac = ac;

        FXMLLoader loader
                = new FXMLLoader(getClass().getResource("ActiviteitenDetailPanel.fxml"));
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

    @FXML
    private void mouseClickItem(MouseEvent event) {
    }

    @Override
    public void loadItems(Object obj) {
        Activiteit activiteit = (Activiteit) obj;
        this.titelLabel.setText(activiteit.getTitel());
        this.lblStartdatumValue.setText(dateFormat.format(activiteit.getStartDatum().getTime()));
        this.lblEinddatumValue.setText(dateFormat.format(activiteit.getEindDatum().getTime()));
        this.lblMaxAantalPlaatsen.setText(String.format("%d", activiteit.getMaxAantalDeelnemers()));

        List<ActiviteitGebruiker> deelnemers = new ArrayList();
        List<ActiviteitGebruiker> begeleiders = new ArrayList();

        //opvullen lists
        ac.getActiviteitenDeelnemers().forEach(d -> {
            if (d.getActiviteit().getId() == activiteit.getId()) {
                deelnemers.add(new ActiviteitGebruiker(d.getGebruiker().getNaam(), d.getGebruiker().getVoornaam(), d.getGebruiker().getTelefoonnummer(), d.getGebruiker().getEmail()));
            }
        });

        ac.getActiviteitenBegeleiders().forEach(b -> {
            if (b.getActiviteit().getId() == activiteit.getId()) {
                begeleiders.add(new ActiviteitGebruiker(b.getGebruiker().getNaam(), b.getGebruiker().getVoornaam(), b.getGebruiker().getTelefoonnummer(), b.getGebruiker().getEmail()));
            }
        });

        this.lblPlaatsenIngenomen.setText(String.format("%d", deelnemers.size() + begeleiders.size()));

        //tableviews opvullen
        tableViewBegeleidersActiviteit.setEditable(false);
        naamBegeleider.setCellValueFactory(new PropertyValueFactory<>("naam"));
        VoornaamBegeleider.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        TelefoonBegeleider.setCellValueFactory(new PropertyValueFactory<>("telefoon"));
        EmailBegeleider.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableViewBegeleidersActiviteit.setItems(FXCollections.observableArrayList(begeleiders));

        tableViewAanwezighedenActiviteit.setEditable(false);
        naamGebruiker.setCellValueFactory(new PropertyValueFactory<>("naam"));
        VoornaamGebruiker.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
        TelefoonGebruiker.setCellValueFactory(new PropertyValueFactory<>("telefoon"));
        EmailGebruiker.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableViewAanwezighedenActiviteit.setItems(FXCollections.observableArrayList(deelnemers));
    }

}
